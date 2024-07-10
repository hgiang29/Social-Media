package com.social.socialapi.service.implement.notification.strategy;

import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.service.NotifierStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostNotifierStrategy implements NotifierStrategy {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    public PostNotifierStrategy(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Integer> getNotifiers(Object entity) {
        Post post = (Post) entity;

        return null;
    }
}
