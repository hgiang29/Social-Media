package com.social.socialapi.service.implement.notification.strategy;

import com.social.socialapi.entity.Follow;
import com.social.socialapi.service.NotifierStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowNotifierStrategy implements NotifierStrategy {
    @Override
    public List<Integer> getNotifiers(Object entity) {
        Follow follow = (Follow) entity;
        List<Integer> notifiers = new ArrayList<>();
        notifiers.add(follow.getUser().getId());

        return notifiers;
    }
}
