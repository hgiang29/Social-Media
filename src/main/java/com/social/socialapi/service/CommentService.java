package com.social.socialapi.service;

import com.social.socialapi.dto.request.CommentDTO;
import com.social.socialapi.entity.post.Comment;

public interface CommentService {
    public Comment addComment(CommentDTO CommentDTO);

    public void deleteComment(int CommentId);

    public Comment getComment(int CommentId);
}