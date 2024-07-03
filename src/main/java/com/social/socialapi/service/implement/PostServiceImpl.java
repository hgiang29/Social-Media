package com.social.socialapi.service.implement;

import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.PostRepository;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
