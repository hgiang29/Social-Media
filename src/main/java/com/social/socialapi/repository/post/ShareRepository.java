package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Share;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ShareRepository extends CrudRepository<Share, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Share c WHERE c.post.id = :IdPost")
    void deleteByPostId(int IdPost);
}
