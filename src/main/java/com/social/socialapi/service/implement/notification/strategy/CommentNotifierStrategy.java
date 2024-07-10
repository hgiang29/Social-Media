package com.social.socialapi.service.implement.notification.strategy;

import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.service.NotifierStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentNotifierStrategy implements NotifierStrategy {

    private final CommentRepository commentRepository;

    public CommentNotifierStrategy(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // strategy: nhung nguoi comment vao cung 1 bai post se nhan duoc thong bao
    @Override
    public List<Integer> getNotifiers(Object entity) {
        Comment comment = (Comment) entity;

        return commentRepository.getCommentUserIdByPostId(comment.getPost().getId());
    }
}