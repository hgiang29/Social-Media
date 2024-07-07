package com.social.socialapi.dto.inputdto;


import jakarta.persistence.Column;
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
//    private int user_id;
    private int postId;
    private PostDTO post;
    private Date createdAt;
    private Date updateAt;
}
