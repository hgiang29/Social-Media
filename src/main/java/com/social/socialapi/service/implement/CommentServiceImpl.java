package com.social.socialapi.service.implement;

import com.social.socialapi.dto.request.CommentDTO;
import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.events.CommentAddedEvent;
import com.social.socialapi.repository.post.CommentRepository;
import com.social.socialapi.repository.post.PostRepository;
import com.social.socialapi.repository.UserRepository;
import com.social.socialapi.service.CommentService;
import com.social.socialapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Comment addComment(CommentDTO CommentDTO) {

//        CommentDTO.setPost(postDTO);

        UserViewDTO userDTO = userRepository.findById(CommentDTO.getUserId()).ConvertEntitytoDTO();
        Post post = postRepository.findById(CommentDTO.getPostId()).orElse(new Post());
        CommentDTO.setCommentUser(userDTO);
        Comment comment = CommentDTO.ConvertCommentDTOtoEntity();
        comment.setPost(post);
        comment.setCreatedAt(Date.from(Instant.now()));
        commentRepository.save(comment);

        eventPublisher.publishEvent(new CommentAddedEvent(this, comment.getUser().getId(), comment.getId()));

        return comment;
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
