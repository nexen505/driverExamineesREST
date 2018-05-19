package com.komarmoss.messaging.service;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.text.MessageFormat;
import java.util.logging.Logger;

@Service
public class SMTPMessageReceiver implements SessionAwareMessageListener<TextMessage> {
    private static final Logger logger = Logger.getLogger(SMTPMessageReceiver.class.getName());

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        logger.info(MessageFormat.format("Received message: {0}", textMessage.getText()));
        try {
            // TODO implement this
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
