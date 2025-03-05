package com.murali.secondpaper.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final JavaMailSender javaMailSender;

    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String toEmail,String body,String subject) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        message.setFrom("expensetracker.nsm.projectet@gmail.com");

        javaMailSender.send(message);

    }
}
