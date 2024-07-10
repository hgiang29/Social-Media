package com.social.socialapi.controller.email;

import com.social.socialapi.service.RedisService;
import com.social.socialapi.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private RedisService redisService;

    @GetMapping("sendEmail")
    public String sendEmail() {
        String confirmUrl = "https://www.facebook.com/hieu.nguyenmai.18";
        String emailContent = "<html>" +
                "<body>" +
                "<p>Đây là email gửi tự động đến tài khoản " + "hieumai943" + ".</p>" +
                "<p>Đây là email tài khoản của bạn.</p>" +
                "<p><a href=\"" + confirmUrl + "\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: #007bff; text-decoration: none; border-radius: 5px;\">Xác thực Email</a></p>" +
                "</body>" +
                "</html>";
        sendEmailService.sendEmail("hieunm.hrt@gmail.com", emailContent , "Vui lòng xác thực email của" +
                " tài khoản "+ "hieumai943");
        return "Email sent";
    }

    @PostMapping("/save")
    public void save(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
    }

    @GetMapping("/get")
    public Object get(@RequestParam String key) {
        return redisService.find(key);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String key) {
        redisService.delete(key);
    }
}