package com.social.socialapi.controller.user;

import com.social.socialapi.dto.response.NotificationViewDTO;
import com.social.socialapi.service.NotificationService;
import com.social.socialapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;

    private final UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping("/notification")
    public ResponseEntity<List<NotificationViewDTO>> getUserNotification(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserIdByUserDetails(userDetails);
        return ResponseEntity.ok(notificationService.getUserNotification(userId));
    }
}
