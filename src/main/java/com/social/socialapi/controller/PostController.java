package com.social.socialapi.controller;

import com.social.socialapi.domain.entity.post.Post;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    public class PostController {

        @Autowired
        private PostService postService;

        @GetMapping("/posts")
        public List<Post> getAllPost() {

            return postService.getAllPosts();
        }

        @GetMapping("/post/{postId}")
        public Post getPost(@PathVariable int postId) {
            return postService.getPostById(postId);
        }

//        @GetMapping("jobPosts/keyword/{keyword}")
//        public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
//            return service.search(keyword);
//
//        }

        @PostMapping("/post")
        public Post addPost(@RequestBody Post post) {
            postService.addPost(post);
            return postService.getPostById(post.getId());
        }
        @PutMapping("/post")
        public Post updatePost(@RequestBody Post post) {
            postService.updatePost(post);
            return postService.getPostById(post.getId());
        }

        @DeleteMapping("post/{postId}")
        public String deletePost(@PathVariable int postId)
        {
            postService.deletePost(postId);
            return "Deleted";
        }
    }
