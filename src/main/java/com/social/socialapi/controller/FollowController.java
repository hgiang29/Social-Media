package com.social.socialapi.controller;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.UserNotFoundException;
import com.social.socialapi.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService service) {
        this.followService = service;
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> followUser(@PathVariable int userId) throws UserNotFoundException {
        followService.followUser(7, userId);
        return ResponseEntity.ok("Create follow relationship successfully!");
    }

    @GetMapping("/follow/follower/{userId}")
    public ResponseEntity<List<UserViewDTO>> getFollowersByUser(@PathVariable int userId) throws UserNotFoundException {
        List<UserViewDTO> followers = followService.getUserFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/follow/following/{userId}")
    public ResponseEntity<List<UserViewDTO>> getFollowingByUser(@PathVariable int userId) throws UserNotFoundException {
        List<UserViewDTO> following = followService.getUserFollowing(userId);
        return ResponseEntity.ok(following);
    }

    @GetMapping("/follow/hello")
    public ResponseEntity<List<UserViewDTO>> helloFollow() throws UserNotFoundException {
        List<UserViewDTO> followers = followService.getUserFollowers(1);
        return ResponseEntity.ok(followers);
    }

    @DeleteMapping("/follow/{userId}")
    public ResponseEntity<String> unFollowUser(@PathVariable int userId) throws UserNotFoundException {
        followService.unFollowUser(7, userId);
        return ResponseEntity.ok("unfollow successfully");

    }


}

