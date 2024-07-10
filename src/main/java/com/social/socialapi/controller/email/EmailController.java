package com.social.socialapi.controller.email;

import com.social.socialapi.service.RedisService;
import com.social.socialapi.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class EmailController {
    @Autowired
    private SendEmailService sendEmailService;
    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public void save(@RequestParam String key, @RequestParam String value) {
        redisService.save(key, value);
        redisService.setExpire(key, 10, TimeUnit.SECONDS);
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
