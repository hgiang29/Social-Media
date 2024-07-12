package com.social.socialapi.dto.response;

import com.social.socialapi.entity.enums.NotificationEntityType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotificationViewDTO {

    private int id;

    private UserViewDTO sender;

    private Date createdAt;

    private String content;

    private boolean isRead;

    private NotificationEntityType entityType;

    private int entityId;

}
