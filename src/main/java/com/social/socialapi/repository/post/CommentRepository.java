package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.post.id = :IdPost")
    void deleteByPostId(int IdPost);

    @Query(value = "SELECT DISTINCT c.comment_user_id FROM comment c WHERE c.post_id = ?1",
            nativeQuery = true)
    List<Integer> getCommentUserIdByPostId(int postId);

    Comment getCommentById(int id);
}
