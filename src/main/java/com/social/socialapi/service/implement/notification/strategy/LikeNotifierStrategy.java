package com.social.socialapi.service.implement.notification.strategy;

import com.social.socialapi.entity.post.Like;
import com.social.socialapi.service.NotifierStrategy;

import java.util.ArrayList;
import java.util.List;

public class LikeNotifierStrategy implements NotifierStrategy {
    @Override
    public List<Integer> getNotifiers(Object entity) {
        Like like = (Like) entity;
        List<Integer> notifiers = new ArrayList<>();
        notifiers.add(like.getPost().getUser().getId());

        return notifiers;
    }
}
