package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;

import com.social.socialapi.entity.post.Comment;

import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.repository.post.LikeRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.LikeService;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    public LikeRepository likeRepository;
    @Autowired
    public PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Like addLike(LikeDTO likeDTO) {

//        likeDTO.setPost(postDTO);

        UserViewDTO userDTO = userRepository.findById(likeDTO.getUserId()).ConvertEntitytoDTO();

        likeDTO.setLikeUser(userDTO);
        Like like = likeDTO.ConvertLikeDTOtoEntity();
        Post post = postRepository.findById(likeDTO.getPostId()).orElse(null);
        like.setPost(post);
        Comment comment = commentRepository.findById(likeDTO.getCommentId()).orElse(null);
        like.setComment(comment);
        like.setCreatedAt(Date.from(Instant.now()));
        like.setUpdatedAt(Date.from(Instant.now()));
        return likeRepository.save(like);
    }

    public void deleteLike(int likeId) {
        try {
            likeRepository.deleteById(likeId);

        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: " + e);
        }
    }

    public Like getLike(int likeId) {
        return likeRepository.findById(likeId).orElse(new Like());
    }
    public List<Like> getLikesByComment(int commentId){
        return likeRepository.getLikesByComment(commentId );
    }


}
