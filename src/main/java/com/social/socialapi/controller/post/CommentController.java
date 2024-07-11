package com.social.socialapi.controller.post;


import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.service.CommentService;
import com.social.socialapi.service.PostService;
import com.social.socialapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/post/comment")
    public ResponseEntity<CommentDTO> addCommentPost(@RequestBody CommentDTO commentDTO, @AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserIdByUserDetails(userDetails);
        commentDTO.setUserId(userId);
        Comment Comment = commentService.addComment(commentDTO);
        CommentDTO responseCommentDTO = commentService.getComment(Comment.getId()).ConvertCommentEntityToDTO();

        messagingTemplate.convertAndSendToUser("3", "/topic/notification", "hello pay");

        return ResponseEntity.ok(responseCommentDTO);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<String> deletecomment(@PathVariable int commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Deleted");
    }

}