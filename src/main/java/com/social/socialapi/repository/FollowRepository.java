package com.social.socialapi.repository;

import com.social.socialapi.domain.entity.Follow;
import org.springframework.data.repository.CrudRepository;

public interface FollowRepository extends CrudRepository<Follow, Integer> {
}
