package com.social.socialapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {

    private final SimpMessagingTemplate messagingTemplate;

    public HelloController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        messagingTemplate.convertAndSendToUser("username", "/queue/messages", "ok men");

        return ResponseEntity.ok("hehe");
    }

    @GetMapping("/jwt")
    public ResponseEntity<String> jwtcheck() {
        return ResponseEntity.ok("jwt works properly");
    }


}
