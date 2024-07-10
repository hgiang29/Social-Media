package com.social.socialapi.service.implement.notification;

import com.social.socialapi.dto.outputdto.NotificationViewDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.Follow;
import com.social.socialapi.entity.User;
import com.social.socialapi.entity.enums.NotificationEntityType;
import com.social.socialapi.entity.notification.Notification;
import com.social.socialapi.entity.notification.NotificationObject;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.events.CommentAddedEvent;
import com.social.socialapi.events.FollowedEvent;
import com.social.socialapi.events.LikeAddedEvent;
import com.social.socialapi.repository.FollowRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.repository.notification.NotificationObjectRepository;
import com.social.socialapi.repository.notification.NotificationRepository;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.repository.post.LikeRepository;
import com.social.socialapi.service.NotificationService;
import com.social.socialapi.service.NotifierStrategy;
import com.social.socialapi.utils.NotificationEntityTypeMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final NotificationObjectRepository notificationObjectRepository;

    private final Map<String, NotifierStrategy> notifierStrategyMap;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final FollowRepository followRepository;

    private final LikeRepository likeRepository;

    private final ModelMapper mapper;


    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationObjectRepository notificationObjectRepository, Map<String, NotifierStrategy> notifierStrategyMap, CommentRepository commentRepository, UserRepository userRepository, FollowRepository followRepository, LikeRepository likeRepository, ModelMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.notificationObjectRepository = notificationObjectRepository;
        this.notifierStrategyMap = notifierStrategyMap;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.likeRepository = likeRepository;
        this.mapper = mapper;
    }

    @EventListener
    @Transactional
    public void handleCommentAddedEvent(CommentAddedEvent event) {
        int commentId = event.getCommentId();
        int senderId = event.getSenderId();

        Comment comment = commentRepository.getCommentById(commentId);

        NotificationEntityType entityType = NotificationEntityType.COMMENT;
        List<Integer> notifierIds = notifierStrategyMap.get("commentNotifierStrategy").getNotifiers(comment);

        createNotification(comment, commentId, senderId, notifierIds);
    }

    @EventListener
    @Transactional
    public void handleFollowAddedEvent(FollowedEvent event) {
        int followId = event.getFollowId();
        int senderId = event.getSenderId();

        Follow follow = followRepository.getFollowById(followId);
        List<Integer> notifierIds = notifierStrategyMap.get("followNotifierStrategy").getNotifiers(follow);

        createNotification(follow, followId, senderId, notifierIds);
    }

    @EventListener
    @Transactional
    public void handleLikeAddedEvent(LikeAddedEvent event) {
        int likeId = event.getLikeId();
        int senderId = event.getSenderId();

        Like like = likeRepository.findById(likeId).get();
        List<Integer> notifierIds = notifierStrategyMap.get("likeNotifierStrategy").getNotifiers(like);

        createNotification(like, likeId, senderId, notifierIds);
    }

    @Override
    public void createNotification(Object entity, int entityId, int senderId, List<Integer> recipientIdList) {
        User sender = userRepository.findById(senderId);

        NotificationEntityType notificationEntityType = NotificationEntityTypeMapper.getEntityType(entity);
        NotificationObject notificationObject = new NotificationObject(notificationEntityType, entityId, sender);
        notificationObjectRepository.save(notificationObject);

        for (int recipientId : recipientIdList) {
            User recipient = userRepository.findById(recipientId);
            Notification notification = new Notification(recipient, notificationObject);
            notificationRepository.save(notification);
        }
    }

    String generateNotificationContent(NotificationObject notificationObject) {

        String action = notificationObject.getEntityType().name();

        return notificationObject.getSender().getFirstName() + " " + notificationObject.getSender().getLastName()
                + " " + action + " your post";
    }

    @Override
    public void readNotification(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId);
        if (!notification.isRead()) {
            notification.setRead(true);
        }
    }

    @Override
    public List<NotificationViewDTO> getUserNotification(int userId) {
        User user = userRepository.findById(userId);
        List<Notification> notificationList = notificationRepository.findByRecipient(user);

        List<NotificationViewDTO> notificationViewDTOList = new ArrayList<>();
        notificationList.forEach(notification -> {
            NotificationViewDTO notificationViewDTO = mapper.map(notification, NotificationViewDTO.class);
            notificationViewDTO.setSender(mapper.map(notification.getNotificationObject().getSender(), UserViewDTO.class));
            notificationViewDTO.setContent(generateNotificationContent(notification.getNotificationObject()));
            notificationViewDTO.setEntityType(notification.getNotificationObject().getEntityType());
            notificationViewDTO.setEntityId(notification.getNotificationObject().getEntityId());

            notificationViewDTOList.add(notificationViewDTO);
        });

        return notificationViewDTOList;
    }


}