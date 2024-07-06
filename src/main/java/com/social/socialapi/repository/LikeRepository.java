package com.social.socialapi.repository;

import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Integer> {
}
