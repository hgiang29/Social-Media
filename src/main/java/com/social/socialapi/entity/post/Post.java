package com.social.socialapi.entity.post;

import com.social.socialapi.dto.inputdto.PostDTO;
import com.social.socialapi.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    @JoinColumn(name = "creator_user_id", nullable = true)
    private User user;

    private Date createdAt;

    private Date updateAt;

    public PostDTO ConvertPostToPostDTO() {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(id);
        postDTO.setContent(content);
        postDTO.setPostUserId(user.getId());
        if (post_img != null) {
            String[] postImgArray = post_img.split(";");
            List<String> postImgList = Arrays.asList(postImgArray);
            postDTO.setPost_imgs(postImgList);
        }
        postDTO.setPost_video(post_video);
        postDTO.setPostUser(user.ConvertEntitytoDTO());
        postDTO.setCreatedAt(createdAt);
        postDTO.setUpdatedAt(updateAt);
        return postDTO;
    }

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
        createdAt = new Date();
        updateAt = new Date();
    }
}
