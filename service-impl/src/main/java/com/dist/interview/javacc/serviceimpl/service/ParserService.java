package com.dist.interview.javacc.serviceimpl.service;

import com.dist.interview.javacc.infra.model.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ParserService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailRequest emailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
        message.setFrom("tphase10@gmail.com");

        mailSender.send(message);

    }
}
