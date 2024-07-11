package com.social.socialapi.controller.post;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.service.LikeService;
import com.social.socialapi.service.PostService;
import com.social.socialapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @PostMapping("/post/like")
    public ResponseEntity<LikeDTO> addLikePost(@RequestBody LikeDTO likeDTO, @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserIdByUserDetails(userDetails);
        likeDTO.setUserId(userId);

        Like like = likeService.addLike(likeDTO);
        LikeDTO responseLikeDTO = likeService.getLike(like.getId()).ConvertLikeEntityToDTO();
        return ResponseEntity.ok(responseLikeDTO);
    }

    @PostMapping("/comment/like")
    public ResponseEntity<LikeDTO> addLikeComment(@RequestBody LikeDTO likeDTO) {
        Like like = likeService.addLike(likeDTO);
        LikeDTO responseLikeDTO = likeService.getLike(like.getId()).ConvertLikeEntityToDTO();
        return ResponseEntity.ok(responseLikeDTO);
    }

    @DeleteMapping("like/{likeId}")
    public ResponseEntity<String> deletelike(@PathVariable int likeId) {

        likeService.deleteLike(likeId);

        return ResponseEntity.ok("Deleted");
    }
}
