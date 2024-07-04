package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
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
//    public void addLikePost(Like like);
    public List<Like> getLikesByPostId(int postId);
}
