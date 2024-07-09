package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Like;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LikeRepository extends CrudRepository<Like, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Like c WHERE c.post.id = :IdPost")
    void deleteByPostId(int IdPost);
}
