package com.social.socialapi.service;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.UserNotFoundException;

import java.util.List;

public interface FollowService {

    void followUser(int userId, int followerId);

    List<UserViewDTO> getUserFollowers(int userId);

    List<UserViewDTO> getUserFollowing(int userId);

    void unFollowUser(int userId, int followingId);

    int getTotalNumberOfFollowers(int userId);

    int getTotalNumberOfFollowings(int userId);

}
