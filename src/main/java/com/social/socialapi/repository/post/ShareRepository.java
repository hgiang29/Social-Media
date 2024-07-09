package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Share;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShareRepository extends CrudRepository<Share, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Share c WHERE c.post.id = :IdPost")
    void deleteByPostId(int IdPost);

    @Query("select share from Share share where share.user.id = :userId")
    List<Share> getShareByUserId(@Param("userId") int userId);
}
