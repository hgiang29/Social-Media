package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
