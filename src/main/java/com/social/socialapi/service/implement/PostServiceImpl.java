package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.PostRepository;
import com.social.socialapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    public PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;

    public List<Post> getAllPosts() {
        try {

            return (List<Post>) postRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: " + e);
            return new ArrayList<>();
        }
    }

    public void addPost(PostDTO postDTO) {
        Post post = ConvertPostDTOtoEntity(postDTO);
        post.setCreatedAt(Date.from(Instant.now()));
        post.setUpdateAt(Date.from(Instant.now()));
        postRepository.save(post);
    }

    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(new Post());
    }

    public void updatePost(PostDTO postDTO) {
        Post post = ConvertPostDTOtoEntity(postDTO);
        post.setUpdateAt(Date.from(Instant.now()));
        postRepository.save(post);
    }

    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }

    //    public void addLikePost(Like like) {
//        postRepository.addLikePost(like);
//    }
    public List<Like> getLikesByPostId(int postId) {
        return postRepository.showLikePost(postId);
    }

    //    public List<Post> search(String keyword) {
//
//        return postRepository.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
//    }
    public Post ConvertPostDTOtoEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setContent(postDTO.getContent());
        post.setPost_img(postDTO.getPost_img());
        post.setPost_video(postDTO.getPost_video());
        return post;
    }
}
