package com.social.socialapi.repository;


import com.social.socialapi.entity.user.Follow;
import com.social.socialapi.entity.user.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

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
        User user = userRepository.findById(3);
        User follower = userRepository.findById(4);
        Follow follow = new Follow(user, follower);

        followRepository.save(follow);
    }

    @Test
    void getFollowerList() {
        User user = userRepository.findById(3);
        List<User> followers = followRepository.getFollowerList(user);

        System.out.println(followers);
    }

    @Test
    void getFollowingList() {
        User user = userRepository.findById(3);

        List<User> followings = followRepository.getFollowingList(user);

        System.out.println(followings);
    }

    @Test
    void listNotFollowUser() {
        List<Integer> users = followRepository.getNotFollowUer(1);
        System.out.println(users.size());
    }
}
