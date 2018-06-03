package com.komarmoss.config;

import com.komarmoss.messaging.service.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.xslt.XsltView;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
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
    public MappingJackson2XmlHttpMessageConverter jaxb2RootElementHttpMessageConverter() {
        Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
        builder.indentOutput(true);
        return new MappingJackson2XmlHttpMessageConverter(builder.build());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
        converters.add(jaxb2RootElementHttpMessageConverter());
        super.addDefaultHttpMessageConverters(converters);
    }

    @Bean
    public ViewResolver getXLTViewResolver() {
        final XsltViewResolver xsltViewResolver = new XsltViewResolver();
        xsltViewResolver.setOrder(1);
        xsltViewResolver.setSourceKey("xmlSource");
        xsltViewResolver.setViewClass(XsltView.class);
        xsltViewResolver.setViewNames("XSLTView");
        xsltViewResolver.setPrefix("/WEB-INF/xsl/");
        xsltViewResolver.setSuffix(".xsl");
        return xsltViewResolver;
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
        final StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
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
        final CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(amqConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);
        return cachingConnectionFactory;
    }

    @Bean
    public DBMessageReceiver getJmsDbMessageReceiver() {
        return new DBMessageReceiver();
    }

    @Bean
    public SMTPMessageReceiver getJmsSMTPMessageReceiver() {
        return new SMTPMessageReceiver();
    }

    @Bean
    public ActiveMQTopic destination() {
        return new ActiveMQTopic("driverApp.topic");
    }

    @NotNull
    private DefaultMessageListenerContainer getDefaultMessageListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
        final DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setDestination(destination);
        return defaultMessageListenerContainer;
    }

    @Bean(name = "dbListenerContainer")
    @Autowired
    public DefaultMessageListenerContainer dbListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
        final DefaultMessageListenerContainer defaultMessageListenerContainer = getDefaultMessageListenerContainer(connectionFactory, destination);
        defaultMessageListenerContainer.setMessageListener(getJmsDbMessageReceiver());
        return defaultMessageListenerContainer;
    }

    @Bean(name = "emailListenerContainer")
    @Autowired
    public DefaultMessageListenerContainer emailListenerContainer(final ConnectionFactory connectionFactory, final Destination destination) {
        final DefaultMessageListenerContainer defaultMessageListenerContainer = getDefaultMessageListenerContainer(connectionFactory, destination);
        defaultMessageListenerContainer.setMessageListener(getJmsSMTPMessageReceiver());
        return defaultMessageListenerContainer;
    }

    @Bean
    @Autowired
    public JmsTemplate jmsTemplate(final ConnectionFactory connectionFactory, final Destination destination) {
        final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(destination);
        return jmsTemplate;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("iliakomarov505@gmail.com");
        mailSender.setPassword("7qwe8rty");

        final Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateChangesMessage() {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello! Due to your subscription to data changes, we notify you for it.\n%s\n");
        return message;
    }

    @Bean
    @Autowired
    public EmailService emailService(JavaMailSender emailSender, SimpleMailMessage changesTemplate) {
        return new EmailServiceImpl(emailSender, changesTemplate);
    }

    @Bean
    public MessageSender messageSender() {
        return new MessageSender();
    }

}
