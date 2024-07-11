package com.social.socialapi.service;

import com.social.socialapi.dto.outputdto.NotificationViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.entity.enums.NotificationEntityType;
import com.social.socialapi.entity.notification.NotificationObject;

import java.util.List;

public interface NotificationService {

    NotificationObject createNotification(Object entity, int entityId, int senderId, List<Integer> recipientIdList);

    List<NotificationViewDTO> getUserNotification(int userId);

    void readNotification(int notificationId);

}
