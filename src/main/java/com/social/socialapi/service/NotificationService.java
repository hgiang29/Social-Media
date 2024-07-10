package com.social.socialapi.service;

import com.social.socialapi.dto.outputdto.NotificationViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.entity.enums.NotificationEntityType;

import java.util.List;

public interface NotificationService {

    void createNotification(Object entity, int entityId, int senderId, List<Integer> recipientIdList);

    List<NotificationViewDTO> getUserNotification(int userId);

    void readNotification(int notificationId);

}
