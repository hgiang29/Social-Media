package com.social.socialapi.service.implement;

import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Post;
import com.social.socialapi.repository.CommentRepository;
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

    public Comment addComment(CommentDTO CommentDTO) {
        Comment Comment = ConvertCommentDTOtoEntity(CommentDTO);
        Comment.setCreatedAt(Date.from(Instant.now()));
        return commentRepository.save(Comment);
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

    public Comment ConvertCommentDTOtoEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        Post post = postService.getPostById(commentDTO.getPostId());
        Comment parent = getComment(commentDTO.getParentId());
        comment.setId(commentDTO.getId());
        comment.setContent(commentDTO.getContent());
        comment.setParent(parent);
        comment.setPost(post);
        return comment;
    }
}
