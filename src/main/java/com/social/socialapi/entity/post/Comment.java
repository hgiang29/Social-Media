package com.social.socialapi.entity.post;

import com.social.socialapi.dto.inputdto.CommentDTO;
import com.social.socialapi.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "comment_user_id", nullable = false)
    private User user;

    private String Content;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parent;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;

    public CommentDTO ConvertCommentEntityToDTO() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(id);
        commentDTO.setContent(Content);
        if (parent != null) {
            commentDTO.setParentId(parent.getId());
            commentDTO.setParent(parent.ConvertCommentEntityToDTO());
        }
        commentDTO.setUserId(user.getId());
        commentDTO.setPostId(post.getId());
        commentDTO.setCommentUser(user.ConvertEntitytoDTO());
//        commentDTO.setPost(post.ConvertPostToPostDTO());
        return commentDTO;
    }

    public Comment(User user, String content, Post post) {
        this.user = user;
        Content = content;
        this.post = post;
        createdAt = new Date();
    }
}
