package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.CommentService;
import com.social.socialapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    public CommentRepository commentRepository;
    @Autowired
    public PostService postService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public Comment addComment(CommentDTO CommentDTO) {
        PostDTO postDTO = postRepository.findById(CommentDTO.getPostId()).orElse(new Post()).ConvertPostToPostDTO();

//        CommentDTO.setPost(postDTO);

        UserViewDTO userDTO = userRepository.findById(CommentDTO.getUserId()).ConvertEntitytoDTO();
        Post post = postRepository.findById(CommentDTO.getPostId()).orElse(new Post());
        CommentDTO.setCommentUser(userDTO);
        Comment comment = CommentDTO.ConvertCommentDTOtoEntity();
        comment.setPost(post);
        comment.setCreatedAt(Date.from(Instant.now()));
        return commentRepository.save(comment);
    }

    public void deleteComment(int CommentId) {
        try {
            commentRepository.deleteById(CommentId);

        } catch (Exception e) {
            System.out.println("Error occurred while fetching posts: " + e);
        }
    }

    public Comment getComment(int CommentId) {
        return commentRepository.findById(CommentId).orElse(new Comment());
    }


}
