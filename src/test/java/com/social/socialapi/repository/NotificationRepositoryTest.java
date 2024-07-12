package com.social.socialapi.repository;

import com.social.socialapi.entity.user.User;
import com.social.socialapi.entity.enums.NotificationEntityType;
import com.social.socialapi.entity.notification.Notification;
import com.social.socialapi.entity.notification.NotificationObject;
import com.social.socialapi.repository.notification.NotificationObjectRepository;
import com.social.socialapi.repository.notification.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NotificationRepositoryTest {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationObjectRepository notificationObjectRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void createNotificationObject() {
        User user = userRepository.findById(4);
        NotificationObject notificationObject = new NotificationObject(NotificationEntityType.LIKE, 4, user);

        notificationObjectRepository.save(notificationObject);
    }

    @Test
    void createNotification() {
        User user = userRepository.findById(3);
        NotificationObject notificationObject = notificationObjectRepository.findById(1);
        Notification notification = new Notification(user, notificationObject);

        notificationRepository.save(notification);
    }

    @Test
    void getUserNotification() {
        User user = userRepository.findById(3);
        List<Notification> notification = notificationRepository.findByRecipient(user);

        System.out.println(notification.size());
    }


}
