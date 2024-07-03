package com.social.socialapi.service;

import com.social.socialapi.domain.entity.post.Post;
import com.social.socialapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

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
        postRepository.save(post);
    }
    public Post getPostById(int id){
        return postRepository.findById(id).orElse(new Post());
    }
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    public void deletePost(int postId) {
        postRepository.deleteById(postId);

    }

//    public List<Post> search(String keyword) {
//
//        return postRepository.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
//    }
}