package com.komarmoss.messaging.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.komarmoss.messaging.model.service.ChangesService;
import com.komarmoss.messaging.model.vo.ChangesMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.text.MessageFormat;
import java.util.logging.Logger;

@Service
public class DBMessageReceiver implements SessionAwareMessageListener<TextMessage> {
    private static final Logger logger = Logger.getLogger(DBMessageReceiver.class.getName());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ChangesService changesService;

    @Override
    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        logger.info(MessageFormat.format("Received message: {0}", textMessage.getText()));
        try {
            final ChangesMessageVO msg = objectMapper.readValue(textMessage.getText(), ChangesMessageVO.class);
            changesService.saveOrUpdateChange(msg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
