package com.mursalin.chat_app.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailSenderUtil {
    private final JavaMailSender mailSender;
    public static final String USER_ACCOUNT_VERIFICATION = "user account verification";


    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${spring.mail.verify.host}")
    private String host;

    @Async
    public void sendSimpleMail(String emailTo, String token) {

        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailFrom);
            mailMessage.setTo(emailTo);
            mailMessage.setSubject(USER_ACCOUNT_VERIFICATION);
            mailMessage.setText(simpleMailText(host, token));

            mailSender.send(mailMessage);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private String simpleMailText(String host, String token) {
        return "Hello sir," + "\n\nThis mail is from Chat app. Click the below link within 5 minutes to update your password.\n\n" +
                getVerificationUrl(host, token) + "\n\nThe support Team, Chat app";
    }

    private String getVerificationUrl(String host, String token) {
        return host + "/chat-app/auth/user/reset-password?token=" + token;
    }
}
