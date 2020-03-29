package com.musicplatform.services.emailService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Properties;

@EnableConfigurationProperties
@PropertySource("classpath:application.properties")
@Service
public class EmailService {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.properties.debug}")
    private boolean debug;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean startTlsEnable;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean auth;

    @Value("${spring.mail.protocol}")
    private String protocol;

    @Autowired
    private JavaMailSenderImpl mailSender;// = new JavaMailSenderImpl();

    private void emailServiceConfiguration() {
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail.smtp.auth", String.valueOf(auth));
        properties.put("mail.smtp.starttls.enable", String.valueOf(startTlsEnable));
        properties.put("mail.debug", String.valueOf(debug));
    }

    public boolean sendSignUpCode(String to, int randomNumber ) {

        try {
            emailServiceConfiguration();
            SimpleMailMessage mailMessage = getMailMessage(to, "Verification code", "Your verification code: " + randomNumber);
            mailSender.send(mailMessage);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public  boolean sendUsername(String to, String username) {

        try {
            emailServiceConfiguration();
            SimpleMailMessage mailMessage = getMailMessage(to, "Music Platform: username", "Your username to sign to platform: " + username);
            mailSender.send(mailMessage);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    private  SimpleMailMessage getMailMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = null;
        try {
            emailServiceConfiguration();
            mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(username);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
        }
        catch (Exception ex) {

        }
        return mailMessage;
    }

}
