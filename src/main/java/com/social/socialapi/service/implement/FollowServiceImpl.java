package com.social.socialapi.service.implement;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.Follow;
import com.social.socialapi.entity.User;
import com.social.socialapi.repository.FollowRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.FollowService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository, ModelMapper mapper) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public void followUser(int userId, int followerId) {
        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerId);
        Follow followRelationship = new Follow(follower, user);

        followRepository.save(followRelationship);
    }


    @Override
    public List<UserViewDTO> getUserFollowers(int userId) {
        User user = userRepository.findById(userId);

        List<User> followers = followRepository.getFollowingList(user);
        List<UserViewDTO> userViewDTOList = new ArrayList<>();
        followers.forEach(follower -> {
            userViewDTOList.add(mapper.map(followers, UserViewDTO.class));
        });

        return userViewDTOList;
    }

    @Override
    public List<UserViewDTO> getUserFollowing(int userId) {
        User user = userRepository.findById(userId);

        List<User> followings = followRepository.getFollowerList(user);
        List<UserViewDTO> userViewDTOList = new ArrayList<>();
        followings.forEach(follower -> {
            userViewDTOList.add(mapper.map(followings, UserViewDTO.class));
        });

        return userViewDTOList;
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
