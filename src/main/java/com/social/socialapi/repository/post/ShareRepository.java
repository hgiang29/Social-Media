package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Share;
import com.social.socialapi.entity.post.Post;
import org.springframework.data.repository.CrudRepository;

public interface ShareRepository extends CrudRepository<Share, Integer> {
}
