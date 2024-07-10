package com.social.socialapi.repository.notification;

import com.social.socialapi.entity.notification.NotificationObject;
import org.springframework.data.repository.CrudRepository;

public interface NotificationObjectRepository extends CrudRepository<NotificationObject, Integer> {

    NotificationObject findById(int id);
}
