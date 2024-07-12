package com.social.socialapi.service;

import com.social.socialapi.dto.response.UserViewDTO;

import java.util.List;

public interface FollowService {

    void followUser(int userId, int followerId);

    List<UserViewDTO> getUserFollowers(int userId);

    List<UserViewDTO> getUserFollowing(int userId);

    void unFollowUser(int userId, int followerId);

    int getTotalNumberOfFollowers(int userId);

    int getTotalNumberOfFollowings(int userId);

    boolean isFollowUser(int meId, int userId);

    List<UserViewDTO> getNotFollowUser(int userId);

}
