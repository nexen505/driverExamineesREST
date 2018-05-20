package com.komarmoss.messaging.service;

import com.komarmoss.messaging.model.vo.ChangesMessageVO;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendChangesMessage(String to, String subject, ChangesMessageVO changes);
}
