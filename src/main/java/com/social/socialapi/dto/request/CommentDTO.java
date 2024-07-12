package com.social.socialapi.dto.request;

import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.entity.post.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int id;
    private int postId;
    private int parentId;
    private int userId;
    private String content;
    //    private PostDTO post;
    private CommentDTO parent;
    private long Likes;
    private Date createdAt;
    private UserViewDTO commentUser;

    public Comment ConvertCommentDTOtoEntity() {
        Comment comment = new Comment();
        comment.setId(id);
//        comment.setPost(post.ConvertDTOtoEntity());
        comment.setContent(content);
        comment.setParent(comment.getParent());
        comment.setUser(commentUser.ConvertDTOtoEntity());
        comment.setCreatedAt(createdAt);
        return comment;
    }
}
