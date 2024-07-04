package com.social.socialapi.dto.inputdto;

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
      private PostDTO post;
//      private User user;
      private Date createdAt;
      private Date updatedAt;
}
