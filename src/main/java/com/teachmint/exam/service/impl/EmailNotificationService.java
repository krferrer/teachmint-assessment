package com.teachmint.exam.service.impl;

import com.teachmint.exam.domain.dto.request.NotificationRequestDto;
import com.teachmint.exam.service.NotificationService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Slf4j
public class EmailNotificationService implements NotificationService {


    private JavaMailSender javaMailSender;

    @Autowired
    public EmailNotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Transactional
    @Async
    public void sendNotification(NotificationRequestDto requestDto) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);


            helper.setTo(requestDto.getTo().toArray(String[]::new));
            helper.setFrom(requestDto.getFrom());
            helper.setSubject(requestDto.getSubject());
            helper.setText(requestDto.getBody());
            javaMailSender.send(message);
        }catch (MessagingException exc){
            log.error(exc.getMessage());
        }
    }
}
