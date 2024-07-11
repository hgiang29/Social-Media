package com.social.socialapi.service.implement;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.Follow;
import com.social.socialapi.entity.User;
import com.social.socialapi.events.FollowedEvent;
import com.social.socialapi.repository.FollowRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.FollowService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    private final ApplicationEventPublisher eventPublisher;

    public FollowServiceImpl(FollowRepository followRepository, UserRepository userRepository, ModelMapper mapper, ApplicationEventPublisher eventPublisher) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void followUser(int userId, int followerId) {
        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerId);
        Follow followRelationship = new Follow(user, follower);

        followRepository.save(followRelationship);

        eventPublisher.publishEvent(new FollowedEvent(this, followRelationship.getId(), followRelationship.getFollower().getId()));
    }


    @Override
    public List<UserViewDTO> getUserFollowers(int userId) {
        User user = userRepository.findById(userId);

        List<User> followers = followRepository.getFollowingList(user);
        List<UserViewDTO> userViewDTOList = new ArrayList<>();
        followers.forEach(follower -> {
            userViewDTOList.add(mapper.map(follower, UserViewDTO.class));
        });

        return userViewDTOList;
    }

    @Override
    public List<UserViewDTO> getUserFollowing(int userId) {
        User user = userRepository.findById(userId);

        List<User> followings = followRepository.getFollowerList(user);
        List<UserViewDTO> userViewDTOList = new ArrayList<>();
        followings.forEach(following -> {
            userViewDTOList.add(mapper.map(following, UserViewDTO.class));
        });

        return userViewDTOList;
    }

    @Override
    public void unFollowUser(int userId, int followerId) {
        User user = userRepository.findById(userId);
        User follower = userRepository.findById(followerId);

        Follow follow = followRepository.getFollowByUserAndFollower(user, follower);
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

    @Override
    public boolean isFollowUser(int userId) {
        User me = userRepository.findById(7);
        User user = userRepository.findById(userId);

        return followRepository.existsByUserAndFollower(user, me);
    }

    @Override
    public List<UserViewDTO> getNotFollowUser(int userId) {
        List<Integer> userIdList = followRepository.getNotFollowUer(userId);
        List<UserViewDTO> userViewDTOList = new ArrayList<>();

        for (int id : userIdList) {
            User user = userRepository.findById(id);
            userViewDTOList.add(mapper.map(user, UserViewDTO.class));
        }

        return userViewDTOList;
    }
}
