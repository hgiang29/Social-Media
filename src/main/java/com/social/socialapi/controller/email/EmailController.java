package com.social.socialapi.controller.email;

import com.social.socialapi.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private SendEmailService sendEmailService;

    @GetMapping("sendEmail")
    public String sendEmail() {
        sendEmailService.sendEmail("hieunm.hrt@gmail.com", "Test body", "Test Subject");
        return "Email sent";
    }
}
