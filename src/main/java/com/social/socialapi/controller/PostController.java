package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
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
        public PostDTO getPost(@PathVariable int postId) {
            Post post = postService.getPostById(postId);
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setContent(post.getContent());
            postDTO.setPost_img(post.getPost_img());
            postDTO.setPost_video(post.getPost_video());
            return postDTO;
        }

//        @GetMapping("jobPosts/keyword/{keyword}")
//        public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
//            return service.search(keyword);
//
//        }

        @PostMapping("/post")
        public Post addPost(@RequestBody PostDTO postDTO) {
            Post post = ConvertPostDTOtoEntity(postDTO);
            postService.addPost(post);
            return postService.getPostById(postDTO.getId());
        }
        @PutMapping("/post")
        public Post updatePost(@RequestBody PostDTO postDTO) {
            Post post = ConvertPostDTOtoEntity(postDTO);
            postService.updatePost(post);
            return postService.getPostById(post.getId());
        }

        @DeleteMapping("post/{postId}")
        public String deletePost(@PathVariable int postId)
        {
            postService.deletePost(postId);
            return "Deleted";
        }
//        @PostMapping("/post/like")
//        public Post addLikePost(@RequestBody LikeDTO likeDTO) {
//            Like like = ConvertLikeDTOtoEntity(likeDTO);
//            postService.addLikePost(like);
//            return postService.getPostById(likeDTO.getPostId());
//        }
        @GetMapping("/post/{postId}/likes")
        public List<Like> getLikesForPost(@PathVariable int postId) {
            return postService.getLikesByPostId(postId);
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
