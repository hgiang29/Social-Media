package com.social.socialapi.entity.notification;

import com.social.socialapi.entity.User;
import com.social.socialapi.entity.enums.NotificationEntityType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NotificationObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private NotificationEntityType entityType;

    private int entityId;

    @ManyToOne
    private User sender;

    private Date createdAt;

    private Date updatedAt;

    public NotificationObject(NotificationEntityType entityType, int entityId, User sender) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.sender = sender;
        createdAt = new Date();
        updatedAt = new Date();
    }
}
