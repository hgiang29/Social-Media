package com.social.socialapi.service;

import com.social.socialapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private RedisService redisService;

    @Value("$(spring.mail.username)")
    private String fromEmailId;

    public void sendEmail(String recipient, String body, User user) {
        String verifyCode = generateRandomString();
        if(body.equals("Register")){
            body = "Mã xác nhận gmail của bạn là: "+ verifyCode;
        }
        if(body.equals("Forget")){
            body = "Mã xác nhận để lấy lại mật khẩu của bạn là: "+ verifyCode;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmailId);
        message.setTo(recipient);
        message.setText(body);
        message.setSubject("Mã xác thực của tài khoản "+ user.getUsername());
        javaMailSender.send(message);
        // Cấu hình redis để lưu verifyCode vào để xác thực xem user điền đúng mã chưa
        redisService.save(user.getEmail(), verifyCode);
        redisService.setExpire(user.getEmail(), 300, TimeUnit.SECONDS);
    }
    public static String generateRandomString() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(8);
        String CHARACTERS =  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }
}
