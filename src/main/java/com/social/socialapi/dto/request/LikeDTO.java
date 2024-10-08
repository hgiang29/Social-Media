package com.social.socialapi.dto.request;

import com.social.socialapi.dto.response.UserViewDTO;
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
public class LikeDTO {
    private int id;
    private int postId;
    private int commentId;
    private int userId;
    //    private PostDTO post;
    private Date createdAt;
    private Date updateAt;
    private UserViewDTO likeUser;
    private CommentDTO comment;

    public Like ConvertLikeDTOtoEntity() {
        Like like = new Like();
        like.setId(id);
//        if(post!= null)
//        like.setPost(post.ConvertDTOtoEntity());
        if (likeUser != null)
            like.setUser(likeUser.ConvertDTOtoEntity());
        like.setCreatedAt(createdAt);
        like.setUpdatedAt(updateAt);
        return like;
    }
}
