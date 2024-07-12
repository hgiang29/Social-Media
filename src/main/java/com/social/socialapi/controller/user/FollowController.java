package com.social.socialapi.controller.user;

import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.exceptions.UserNotFoundException;
import com.social.socialapi.service.FollowService;
import com.social.socialapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FollowController {

    private final FollowService followService;

    private final UserService userService;

    public FollowController(FollowService service, UserService userService) {
        this.followService = service;
        this.userService = userService;
    }

    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> followUser(@PathVariable int userId, @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        int meId = userService.getUserIdByUserDetails(userDetails);
        followService.followUser(userId, meId);
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
    public ResponseEntity<List<UserViewDTO>> helloFollow(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        int meId = userService.getUserIdByUserDetails(userDetails);
        List<UserViewDTO> followers = followService.getUserFollowers(meId);
        return ResponseEntity.ok(followers);
    }

    @DeleteMapping("/follow/{userId}")
    public ResponseEntity<String> unFollowUser(@PathVariable int userId, @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        int meId = userService.getUserIdByUserDetails(userDetails);
        followService.unFollowUser(userId, meId);
        return ResponseEntity.ok("unfollow successfully");

    }


    @GetMapping("/follow/recommend")
    public ResponseEntity<List<UserViewDTO>> getUserFollowRecommend(@AuthenticationPrincipal UserDetails userDetails) {
        int meId = userService.getUserIdByUserDetails(userDetails);
        return ResponseEntity.ok(followService.getNotFollowUser(meId));
    }
//


}

