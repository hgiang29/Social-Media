package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<LikeDTO> likeDTOList = new ArrayList<>();
        List<Like> likes = getLikesForPost(postDTO.getId());
        for (Like like : likes) {
            LikeDTO likeDTO = new LikeDTO();
            likeDTO.setId(like.getId());
            likeDTO.setPostId(like.getPost().getId());
            likeDTO.setCreatedAt(like.getCreatedAt());
            likeDTO.setUpdateAt(like.getUpdatedAt());
            likeDTOList.add(likeDTO);
        }
        postDTO.setLikeDTOs(likeDTOList);
        return postDTO;
    }

//        @GetMapping("jobPosts/keyword/{keyword}")
//        public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
//            return service.search(keyword);
//
//        }

    @PostMapping("/post")
    public Post addPost(@RequestBody PostDTO postDTO) {
        postService.addPost(postDTO);
        return postService.getPostById(postDTO.getId());
    }
    @PutMapping("/post")
    public Post updatePost(@RequestBody PostDTO postDTO) {
        postService.updatePost(postDTO);
        return postService.getPostById(postDTO.getId());
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


}
