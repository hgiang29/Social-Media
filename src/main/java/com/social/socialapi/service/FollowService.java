package com.social.socialapi.service;

import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.UserNotFoundException;

import java.util.List;

public interface FollowService {

    void followUser(int userId, int followerId) throws UserNotFoundException;

    List<User> getUserFollowers(int userId) throws UserNotFoundException;

    List<User> getUserFollowing(int userId) throws UserNotFoundException;

    void unFollowUser(int userId, int followingId) throws UserNotFoundException;

    int getTotalNumberOfFollowers(int userId) throws UserNotFoundException;

    int getTotalNumberOfFollowings(int userId) throws UserNotFoundException;

}
