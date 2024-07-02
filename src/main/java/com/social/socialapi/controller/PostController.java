package com.social.socialapi.controller;

import com.social.socialapi.domain.entity.post.Post;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
    public class PostController {

        @Autowired
        private PostService postService;

        @GetMapping("/post")
        public List<Post> getAllJobs() {
            return postService.getAllPosts();
        }
    }
