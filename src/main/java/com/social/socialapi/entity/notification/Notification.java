package com.social.socialapi.entity.notification;

import com.social.socialapi.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User recipient;

    @ManyToOne
    private NotificationObject notificationObject;

    private Date createdAt;

    private boolean isRead;

    public Notification(User recipient, NotificationObject notificationObject) {
        this.recipient = recipient;
        this.notificationObject = notificationObject;
        this.createdAt = new Date();
        isRead = false;
    }
}
