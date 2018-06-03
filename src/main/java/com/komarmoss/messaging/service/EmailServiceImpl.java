package com.komarmoss.messaging.service;

import com.komarmoss.messaging.model.vo.ChangesMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Objects;

@Component
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    private final SimpleMailMessage changesTemplate;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender, SimpleMailMessage changesTemplate) {
        this.emailSender = emailSender;
        this.changesTemplate = changesTemplate;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendChangesMessage(String to, String subject, ChangesMessageVO changes) {
        final String changesInfo = MessageFormat.format("Date: {0,date,long}\nType: {1}\nEntity: {2}\nDetails:\n\t{3}",
                changes.getTime(),
                Objects.toString(changes.getType()),
                changes.getChangedEntityClass(),
                changes.getDetails());
        sendSimpleMessage(to, subject, String.format(changesTemplate.getText(), changesInfo));
    }
}
