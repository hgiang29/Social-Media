package com.social.socialapi.repository;

import com.social.socialapi.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}