package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import com.social.socialapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface PostService {

    public List<Post> getAllPosts();

    public void addPost(PostDTO postDTO);

    public Post getPostById(int id);

    public void uploadImage(final Integer id, final MultipartFile file);

    public void updatePost(PostDTO postDTO);

    public void deletePost(int postId);

    //    public void addLikePost(Like like);
    public List<Like> getLikesByPostId(int postId);
    public List<Share> getSharesByPostId(int postId);
}
