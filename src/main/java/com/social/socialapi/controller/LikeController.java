package com.social.socialapi.controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.dto.outputdto.MessageViewDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.service.LikeService;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private PostService postService;

    @PostMapping("/post/like")
    public ResponseEntity<LikeDTO> addLikePost(@RequestBody LikeDTO likeDTO) {
        Like like = likeService.addLike(likeDTO);
        LikeDTO responseLikeDTO = ConvertEntityToDTO(likeService.getLike(like.getId()));
        return ResponseEntity.ok(responseLikeDTO);
    }

    public LikeDTO ConvertEntityToDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setPostId(like.getPost().getId());
        likeDTO.setId(like.getId());
        return likeDTO;
    }
}
