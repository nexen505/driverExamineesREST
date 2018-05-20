package com.komarmoss.config;

import com.komarmoss.messaging.service.DBMessageReceiver;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Configuration
@ComponentScan("com.komarmoss")
@EnableWebMvc
@EnableTransactionManagement
public class AppWebConfig extends WebMvcConfigurationSupport {

    @Bean
    public CustomObjectMapper customObjectMapper() {
        return new CustomObjectMapper();
    }

    @Bean
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(customObjectMapper());
    }

    @Bean
    public Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
        return new Jaxb2RootElementHttpMessageConverter();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
        converters.add(jaxb2RootElementHttpMessageConverter());
        super.addDefaultHttpMessageConverters(converters);
    }

    @Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
        jndiDataSourceLookup.setResourceRef(true);
        return jndiDataSourceLookup.getDataSource("jdbc/ExdrPool");
    }

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(serviceRegistryBuilder.build());
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory, DataSource dataSource) {
        final HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setDataSource(dataSource);
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public ActiveMQConnectionFactory amqConnectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(amqConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);
        return cachingConnectionFactory;
    }

    @Bean
    public DBMessageReceiver getJmsMessageReceiver() {
        return new DBMessageReceiver();
    }

    @Bean
    public ActiveMQQueue destination() {
        return new ActiveMQQueue("queue.queue_in");
    }

    @Bean
    public DefaultMessageListenerContainer messageListenerContainer() {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory());
        defaultMessageListenerContainer.setDestination(destination());
        defaultMessageListenerContainer.setMessageListener(getJmsMessageReceiver());
        return defaultMessageListenerContainer;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestination(destination());
        return jmsTemplate;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

// TODO use valid credentials
        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateChangesMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello! Due to your subscription to data changes, we notify you for it.\n%s\n");
        return message;
    }

}
