package com.social.socialapi.controller;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.service.LikeService;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Like addLikePost(@RequestBody LikeDTO likeDTO) {
        try {
            Like like = ConvertLikeDTOtoEntity(likeDTO);
            likeService.addLike(like);
            return likeService.getLike(like.getId());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Like();
    }
    public Like ConvertLikeDTOtoEntity(LikeDTO likeDTO) {
        Like like = new Like();
        Post post = postService.getPostById(likeDTO.getPostId());
        like.setId(likeDTO.getId());
        like.setPost(post);
        return like;
    }
    public Post ConvertPostDTOtoEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setContent(postDTO.getContent());
        post.setPost_img(postDTO.getPost_img());
        post.setPost_video(postDTO.getPost_video());
        return post;
    }
}
