package com.social.socialapi.dto.inputdto;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.post.Comment;
import com.social.socialapi.entity.post.Like;
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
    private PostDTO post;
    private CommentDTO parent;
    private Date createdAt;
    private UserViewDTO commentUser;
    
    public Comment ConvertCommentDTOtoEntity() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setPost(post.ConvertDTOtoEntity());
        comment.setContent(content);
        comment.setParent(comment.getParent());
        comment.setUser(commentUser.ConvertDTOtoEntity());
        comment.setCreatedAt(createdAt);
        return comment;
    }
}
