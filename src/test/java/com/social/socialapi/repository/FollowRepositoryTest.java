package com.social.socialapi.repository;


import com.social.socialapi.entity.Follow;
import com.social.socialapi.entity.User;

import com.social.socialapi.repository.post.FollowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FollowRepositoryTest {

    @Autowired
    FollowRepository followRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void createFollowRelationship() {
        User user = userRepository.findById(1).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(1)));
        User follower = userRepository.findById(2).
                orElseThrow(() -> new EntityNotFoundException(String.valueOf(2)));
        Follow followRelationship = new Follow(follower, user);

        followRepository.save(followRelationship);
    }
}
