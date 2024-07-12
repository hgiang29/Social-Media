package com.social.socialapi.service.implement.notification.strategy;

import com.social.socialapi.entity.user.User;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.FollowRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.service.NotifierStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostNotifierStrategy implements NotifierStrategy {

    private final PostRepository postRepository;

    private final FollowRepository followRepository;


    public PostNotifierStrategy(PostRepository postRepository, FollowRepository followRepository) {
        this.postRepository = postRepository;
        this.followRepository = followRepository;
    }

    @Override
    public List<Integer> getNotifiers(Object entity) {
        Post post = (Post) entity;
        User postCreator = post.getUser();
        List<User> notifiers = followRepository.getFollowerList(postCreator);

        List<Integer> notifierIds = new ArrayList<>();
        notifiers.forEach(notifier -> notifierIds.add(notifier.getId()));

        return notifierIds;
    }
}
