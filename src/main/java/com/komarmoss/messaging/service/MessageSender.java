package com.komarmoss.messaging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import java.io.Serializable;
import java.util.logging.Logger;

@Service
public class MessageSender {
    private static final Logger logger = Logger.getLogger(MessageSender.class.getName());

    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(final Serializable obj) {
        try {
            final String text = objectMapper.writeValueAsString(obj);
            this.jmsTemplate.send(session -> {
                Message message = session.createTextMessage(text);
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                return message;
            });
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public void send(final String text) {
        try {
            this.jmsTemplate.send(session -> {
                Message message = session.createTextMessage(text);
                message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
                return message;
            });
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}