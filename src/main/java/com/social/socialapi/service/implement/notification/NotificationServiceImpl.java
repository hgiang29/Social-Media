package com.social.socialapi.service.implement.notification;

import com.social.socialapi.dto.response.NotificationViewDTO;
import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.entity.user.Follow;
import com.social.socialapi.entity.user.User;
import com.social.socialapi.entity.enums.NotificationEntityType;
import com.social.socialapi.entity.notification.Notification;
import com.social.socialapi.entity.notification.NotificationObject;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.events.CommentAddedEvent;
import com.social.socialapi.events.FollowedEvent;
import com.social.socialapi.events.LikeAddedEvent;
import com.social.socialapi.events.PostAddedEvent;
import com.social.socialapi.repository.FollowRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.repository.notification.NotificationObjectRepository;
import com.social.socialapi.repository.notification.NotificationRepository;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.repository.post.LikeRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.service.NotificationService;
import com.social.socialapi.service.NotifierStrategy;
import com.social.socialapi.utils.NotificationEntityTypeMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    private final PostRepository postRepository;

    private final ModelMapper mapper;

    private final SimpMessagingTemplate messagingTemplate;


    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationObjectRepository notificationObjectRepository, Map<String, NotifierStrategy> notifierStrategyMap, CommentRepository commentRepository, UserRepository userRepository, FollowRepository followRepository, LikeRepository likeRepository, PostRepository postRepository, ModelMapper mapper, SimpMessagingTemplate messagingTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationObjectRepository = notificationObjectRepository;
        this.notifierStrategyMap = notifierStrategyMap;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.followRepository = followRepository;
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    @Transactional
    public void handleCommentAddedEvent(CommentAddedEvent event) {
        int commentId = event.getCommentId();
        int senderId = event.getSenderId();

        Comment comment = commentRepository.getCommentById(commentId);

        NotificationEntityType entityType = NotificationEntityType.COMMENT;
        List<Integer> notifierIds = notifierStrategyMap.get("commentNotifierStrategy").getNotifiers(comment);

        NotificationObject notificationObject = createNotification(comment, commentId, senderId, notifierIds);
        sendLiveNotificationToNotifiers(notifierIds, notificationObject);
    }

    @EventListener
    @Transactional
    public void handleFollowAddedEvent(FollowedEvent event) {
        int followId = event.getFollowId();
        int senderId = event.getSenderId();

        Follow follow = followRepository.getFollowById(followId);
        List<Integer> notifierIds = notifierStrategyMap.get("followNotifierStrategy").getNotifiers(follow);

        NotificationObject notificationObject = createNotification(follow, followId, senderId, notifierIds);
        sendLiveNotificationToNotifiers(notifierIds, notificationObject);
    }

    @EventListener
    @Transactional
    public void handleLikeAddedEvent(LikeAddedEvent event) {
        int likeId = event.getLikeId();
        int senderId = event.getSenderId();

        Like like = likeRepository.findById(likeId).get();
        List<Integer> notifierIds = notifierStrategyMap.get("likeNotifierStrategy").getNotifiers(like);

        NotificationObject notificationObject = createNotification(like, likeId, senderId, notifierIds);
        sendLiveNotificationToNotifiers(notifierIds, notificationObject);
    }

    @EventListener
    @Transactional
    public void handlePostAddedEvent(PostAddedEvent event) {
        int postId = event.getPostId();
        int senderId = event.getSenderId();

        Post post = postRepository.findById(postId).get();
        List<Integer> notifierIds = notifierStrategyMap.get("postNotifierStrategy").getNotifiers(post);

        NotificationObject notificationObject = createNotification(post, postId, senderId, notifierIds);
        sendLiveNotificationToNotifiers(notifierIds, notificationObject);
    }

    @Override
    public NotificationObject createNotification(Object entity, int entityId, int senderId, List<Integer> recipientIdList) {
        User sender = userRepository.findById(senderId);

        NotificationEntityType notificationEntityType = NotificationEntityTypeMapper.getEntityType(entity);
        NotificationObject notificationObject = new NotificationObject(notificationEntityType, entityId, sender);
        notificationObjectRepository.save(notificationObject);

        for (int recipientId : recipientIdList) {
            User recipient = userRepository.findById(recipientId);
            Notification notification = new Notification(recipient, notificationObject);
            notificationRepository.save(notification);
        }

        return notificationObject;
    }

    String generateNotificationContent(NotificationObject notificationObject) {

        String notificationContent = notificationObject.getSender().getFirstName() + " ";

        switch (notificationObject.getEntityType()) {
            case LIKE -> notificationContent = notificationContent + " Like your post";
            case COMMENT -> notificationContent = notificationContent + " also comment on the post you commented";
            case FOLLOW -> notificationContent = notificationContent + " started to follow you";
            case POST -> notificationContent = notificationContent + " created a new post";
        }

        return notificationContent;
    }

    void sendLiveNotificationToNotifiers(List<Integer> notifiers, NotificationObject notificationObject) {

        String noti = generateNotificationContent(notificationObject);

        for (int notifier : notifiers) {
            User user = userRepository.findById(notifier);
            messagingTemplate.convertAndSendToUser(String.valueOf(user.getId()), "/queue/private", noti);
        }

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
