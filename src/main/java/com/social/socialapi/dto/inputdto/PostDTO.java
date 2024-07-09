package com.social.socialapi.dto.inputdto;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int id;
    private String content;
    private List<String> post_imgs;
    private int postUserId;
    private String post_video;
    private long likeDTOs;
    private UserViewDTO postUser;
    private long shareDTOS;
    private long commentDTOS;
    public Post ConvertDTOtoEntity(){
        Post post = new Post();
        post.setId(id);
        post.setContent(content);
        post.setPost_img(String.join(";", post_imgs));
        post.setPost_video(post_video);
        post.setUser(postUser.ConvertDTOtoEntity());
        return post;
    }
}
