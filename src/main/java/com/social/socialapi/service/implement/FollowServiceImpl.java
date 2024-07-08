package com.social.socialapi.service.implement;

import com.social.socialapi.entity.Follow;
import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.UserNotFoundException;
import com.social.socialapi.repository.FollowRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.FollowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void followUser(int userId, int followerId) {
        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerId);
        Follow followRelationship = new Follow(follower, user);

        followRepository.save(followRelationship);
    }


    @Override
    public List<User> getUserFollowers(int userId) {
        User user = userRepository.findById(userId);
        return followRepository.getFollowerList(user);
    }

    @Override
    public List<User> getUserFollowing(int userId) {
        User user = userRepository.findById(userId);
        return followRepository.getFollowingList(user);
    }

    @Override
    public void unFollowUser(int userId, int followingId) {
        User user = userRepository.findById(userId);
        User following = userRepository.findById(followingId);

        Follow follow = followRepository.getFollowByUserAndFollower(following, user);
        followRepository.delete(follow);
    }

    @Override
    public int getTotalNumberOfFollowers(int userId) {
        return this.getUserFollowers(userId).size();
    }

    @Override
    public int getTotalNumberOfFollowings(int userId) {
        return this.getUserFollowing(userId).size();
    }
}
