package com.social.socialapi.repository.post;

import com.social.socialapi.entity.post.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Integer> {
}
