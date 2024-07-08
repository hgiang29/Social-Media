package com.social.socialapi.dto.inputdto;

import com.social.socialapi.dto.outputdto.UserViewDTO;
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
    private String content;
    private PostDTO post;
    private CommentDTO parent;
    //      private User user;
    private Date createdAt;
    //    private Date updateAt;
    private UserViewDTO commentUser;

}
