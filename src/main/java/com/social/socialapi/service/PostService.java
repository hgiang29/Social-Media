package com.social.socialapi.service;

import com.social.socialapi.domain.entity.post.Post;
import com.social.socialapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    public PostRepository postRepository;

    public List<Post> getAllPosts(){
        return (List<Post>) postRepository.findAll();
    }
}
