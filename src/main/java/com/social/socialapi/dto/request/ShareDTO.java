package com.social.socialapi.dto.request;


import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.entity.post.Share;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShareDTO {
    private int id;
    private int postId;
    private int user_id;
    //    private PostDTO post;
    private Date createdAt;
    private Date updateAt;
    private UserViewDTO shareUser;

    public Share ConvertShareDTOtoEntity() {
        Share share = new Share();
        share.setId(id);
//        share.setPost(post.ConvertDTOtoEntity());
        share.setUser(shareUser.ConvertDTOtoEntity());
        share.setCreatedAt(createdAt);
        share.setUpdateAt(updateAt);
        return share;
    }
}
