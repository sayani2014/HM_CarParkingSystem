package com.carparkingsystem.adminservice.utils;

import com.carparkingsystem.adminservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(UserDTO response) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("sayani.koley@happiestminds.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
    }
}
