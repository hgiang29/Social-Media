package com.social.socialapi.repository.notification;

import com.social.socialapi.entity.User;
import com.social.socialapi.entity.notification.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    Notification findById(int id);

    List<Notification> findByRecipient(User recipient);
}
