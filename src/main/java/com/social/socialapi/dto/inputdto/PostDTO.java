package com.social.socialapi.dto.inputdto;

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
    private String post_img;

    private String post_video;
    private List<LikeDTO> likeDTOs;
}
