package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.PostRepository;
import com.social.socialapi.service.PostService;
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

    public List<Post> getAllPosts(){
        try {

            return (List<Post>) postRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: "+ e);
            return new ArrayList<>();
        }
    }
    public void addPost(Post post){
        post.setCreatedAt(Date.from(Instant.now()));
        post.setUpdateAt(Date.from(Instant.now()));
        postRepository.save(post);
    }
    public Post getPostById(int id){
        return postRepository.findById(id).orElse(new Post());
    }
    public void updatePost(Post post) {
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
}
