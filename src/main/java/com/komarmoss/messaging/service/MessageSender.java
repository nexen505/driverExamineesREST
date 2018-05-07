package com.komarmoss.messaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import java.util.logging.Logger;

@Service
public class MessageSender {
    private static final Logger logger = Logger.getLogger(MessageSender.class.getName());

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        this.jmsTemplate.send(session -> {
            Message message = session.createTextMessage(text);
            message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            return message;
        });
    }
}
