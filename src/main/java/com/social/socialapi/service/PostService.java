package com.social.socialapi.service;

import com.social.socialapi.dto.request.PostDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.entity.post.Share;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    public List<Post> getAllPosts();

    public List<Post> getAllPostsByUserId(int UserId);

    public PostDTO addPost(String content, Integer userId, final List<MultipartFile> files);

    public PostDTO addPost(String content, Integer userId);

    public Post getPostById(int id);

    public void uploadImage(final Integer id, final MultipartFile file);

    public void updatePost(PostDTO postDTO);

    public void deletePost(int postId);

    //    public void addLikePost(Like like);
    public List<Like> getLikesByPostId(int postId);

    public List<Share> getSharesByPostId(int postId);

    public List<Comment> getCommentsByPostId(int postId);
}
