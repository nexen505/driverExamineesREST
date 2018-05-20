package com.komarmoss.messaging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.komarmoss.messaging.model.dao.SubscriberDAO;
import com.komarmoss.messaging.model.entity.SubscriberEntity;
import com.komarmoss.messaging.model.vo.ChangesMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class SMTPMessageReceiver implements SessionAwareMessageListener<TextMessage> {
    private static final Logger logger = Logger.getLogger(SMTPMessageReceiver.class.getName());

    private final ObjectMapper objectMapper;

    private final SubscriberDAO subscriberDAO;

    private final EmailService emailService;

    @Autowired
    public SMTPMessageReceiver(ObjectMapper objectMapper, SubscriberDAO subscriberDAO, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.subscriberDAO = subscriberDAO;
        this.emailService = emailService;
    }

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        logger.info(MessageFormat.format("Received message: {0}", textMessage.getText()));
        try {
            final ChangesMessageVO msg = objectMapper.readValue(textMessage.getText(), ChangesMessageVO.class);
            final List<SubscriberEntity> subscribers = subscriberDAO.getSubscribers(msg.getType(), msg.getChangedEntityClass());
            subscribers.forEach(subscriber -> {
                emailService.sendChangesMessage(subscriber.getEmail(),
                        String.format("%s: changes notification", msg.getChangedEntityClass()),
                        msg);
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
