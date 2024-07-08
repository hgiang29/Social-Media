package com.social.socialapi.entity.post;

import com.social.socialapi.dto.inputdto.PostDTO;
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private String post_img;

    private String post_video;
    @OneToOne
    @JoinColumn(name = "creator_user_id", nullable = false)
    private User user;

    private Date createdAt;

    private Date updateAt;
    public PostDTO ConvertPostToPostDTO(){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(id);
        postDTO.setContent(content);
        postDTO.setPostUserId(user.getId());
        postDTO.setPost_img(post_img);
        postDTO.setPost_video(post_video);
        postDTO.setPostUser(user.ConvertEntitytoDTO());
        return postDTO;
    }
}
