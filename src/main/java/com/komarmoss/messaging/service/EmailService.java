package com.komarmoss.messaging.service;

import com.komarmoss.messaging.model.vo.ChangesMessageVO;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendTemplateMessage(String to, String subject, Object... templateArgs);

    void sendChangesMessage(String to, String subject, ChangesMessageVO changes);
}
