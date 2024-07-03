package com.social.socialapi.service;

import com.social.socialapi.domain.entity.post.Post;
import com.social.socialapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface PostService {

    public List<Post> getAllPosts();
    public void addPost(Post post);
    public Post getPostById(int id) ;
    public void updatePost(Post post);
    public void deletePost(int postId);
}
