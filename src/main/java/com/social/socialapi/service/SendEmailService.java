package com.social.socialapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("$(spring.mail.username)")
    private String fromEmailId;

    public void sendEmail(String recipient, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmailId);
        message.setTo(recipient);
        message.setText(body);
        message.setSubject(subject);

        javaMailSender.send(message);
    }
}
