package com.social.socialapi.utils;

import com.social.socialapi.dto.outputdto.NotificationViewDTO;
import com.social.socialapi.entity.Follow;
import com.social.socialapi.entity.enums.NotificationEntityType;
import com.social.socialapi.entity.notification.Notification;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;

public class NotificationEntityTypeMapper {

    public static NotificationEntityType getEntityType(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }

        Class<?> entityClass = entity.getClass();

        if (entityClass.equals(Post.class)) {
            return NotificationEntityType.POST;
        } else if (entityClass.equals(Comment.class)) {
            return NotificationEntityType.COMMENT;
        } else if (entityClass.equals(Like.class)) {
            return NotificationEntityType.LIKE;
        } else if (entityClass.equals(Follow.class)) {
            return NotificationEntityType.FOLLOW;
        } else {
            throw new IllegalArgumentException("Unknown entity type: " + entityClass.getSimpleName());
        }

    }


}
