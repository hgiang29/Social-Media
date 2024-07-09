package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    //    void addLikePost(Like like);
    @Query("select like from Like like where like.post.id = :IdPost")
    List<Like> showLikePost(@Param("IdPost") int IdPost);

    @Query("select share from Share share where share.post.id = :IdPost")
    List<Share> showSharePost (@Param("IdPost") int IdPost);

    @Query("select comment from Comment comment where comment.post.id = :IdPost")
    List<Comment> showCommentPost (@Param("IdPost") int IdPost);

    @Query("select post from Post post where post.user.id = :userId")
    List<Post> findAllByUserId(@Param("userId") int userId);
}
