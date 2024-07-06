package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPost() {

        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable int postId) {
        Post post = new Post();
        try {
             post = postService.getPostById(postId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        PostDTO postDTO = ConvertPostEntityToDTO(post);
        List<LikeDTO> likeDTOList = new ArrayList<>();
        List<Like> likes = getLikesForPost(postDTO.getId());
        for (Like like : likes) {
            LikeDTO likeDTO = ConvertLikeEntityToDTO(like);
            likeDTOList.add(likeDTO);
        }
        postDTO.setLikeDTOs(likeDTOList);
        return ResponseEntity.ok(postDTO);
    }

//        @GetMapping("jobPosts/keyword/{keyword}")
//        public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
//            return service.search(keyword);
//
//        }

    @PostMapping("/post")
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO) {
        postService.addPost(postDTO);
        PostDTO ResponsePostDTO = ConvertPostEntityToDTO(postService.getPostById(postDTO.getId()));
        return ResponseEntity.ok(ResponsePostDTO);
    }

    @PostMapping("/post/image/{id}")
    public ResponseEntity<PostDTO> addImage(@PathVariable final Integer id, @RequestPart final MultipartFile file) {
        this.postService.uploadImage(id, file);
        return ResponseEntity.ok(ConvertPostEntityToDTO(postService.getPostById(id)));
    }
    @PutMapping("/post")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        postService.updatePost(postDTO);
        PostDTO ResponsePostDTO = ConvertPostEntityToDTO(postService.getPostById(postDTO.getId()));
        return ResponseEntity.ok(ResponsePostDTO);
    }

    @DeleteMapping("post/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/post/{postId}/likes")
    public List<Like> getLikesForPost(@PathVariable int postId) {
        return postService.getLikesByPostId(postId);
    }

    public PostDTO ConvertPostEntityToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setPost_img(post.getPost_img());
        postDTO.setPost_video(post.getPost_video());
        return postDTO;
    }

    public LikeDTO ConvertLikeEntityToDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setPostId(like.getPost().getId());
        likeDTO.setId(like.getId());
        likeDTO.setId(like.getId());
        return likeDTO;
    }

}
