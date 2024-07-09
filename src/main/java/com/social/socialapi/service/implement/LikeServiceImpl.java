package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.LikeDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.entity.post.Like;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.post.LikeRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.LikeService;
import com.social.socialapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    public LikeRepository likeRepository;
    @Autowired
    public PostService postService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Like addLike(LikeDTO likeDTO) {
        PostDTO postDTO = postRepository.findById(likeDTO.getPostId()).orElse(new Post()).ConvertPostToPostDTO();
//        likeDTO.setPost(postDTO);

        UserViewDTO userDTO = userRepository.findById(likeDTO.getUserId()).ConvertEntitytoDTO();
        Post post = postRepository.findById(likeDTO.getPostId()).orElse(new Post());
        likeDTO.setLikeUser(userDTO);
        Like like = likeDTO.ConvertLikeDTOtoEntity();
        like.setPost(post);
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


}
