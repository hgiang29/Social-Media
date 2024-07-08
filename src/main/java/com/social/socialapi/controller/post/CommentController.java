package com.social.socialapi.controller.post;


import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.service.CommentService;
import com.social.socialapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;

    @PostMapping("/post/comment")
    public ResponseEntity<CommentDTO> addCommentPost(@RequestBody CommentDTO commentDTO) {
        Comment Comment = commentService.addComment(commentDTO);
        CommentDTO responseCommentDTO = ConvertEntityToDTO(commentService.getComment(Comment.getId()));
        return ResponseEntity.ok(responseCommentDTO);
    }

    public CommentDTO ConvertEntityToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        if (comment.getParent() != null) {
            commentDTO.setParentId(comment.getParent().getId());
        }
        commentDTO.setPostId(comment.getPost().getId());
        return commentDTO;
    }
}