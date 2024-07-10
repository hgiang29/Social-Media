package com.social.socialapi.controller;

import com.social.socialapi.dto.outputdto.NotificationViewDTO;
import com.social.socialapi.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notification")
    public ResponseEntity<List<NotificationViewDTO>> getUserNotification() {
        return ResponseEntity.ok(notificationService.getUserNotification(8));
    }
}
