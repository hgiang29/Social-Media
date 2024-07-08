package com.social.socialapi.controller;

import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.UserNotFoundException;
import com.social.socialapi.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService service) {
        this.followService = service;
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> followUser(@PathVariable int userId) throws UserNotFoundException {
        followService.followUser(3, userId);
        return ResponseEntity.ok("Create follow relationship successfully");
    }

    @GetMapping("/follow/follower/{userId}")
    public ResponseEntity<List<User>> getFollowersByUser(@PathVariable int userId) throws UserNotFoundException {
        List<User> followers = followService.getUserFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/follow/following/{userId}")
    public ResponseEntity<List<User>> getFollowingByUser(@PathVariable int userId) throws UserNotFoundException {
        List<User> following = followService.getUserFollowing(userId);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/follow/hello")
    public ResponseEntity<List<User>> helloFollow() throws UserNotFoundException {
        List<User> followers = followService.getUserFollowers(1);
        return ResponseEntity.ok(followers);
    }

    @DeleteMapping("/follow/{userId}")
    public ResponseEntity<String> unFollowUser(@PathVariable int userId) throws UserNotFoundException {
        followService.unFollowUser(3, userId);
        return ResponseEntity.ok("unfollow successfully");

    }


}

