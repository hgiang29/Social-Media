package com.social.socialapi.dto.outputdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String profile_pic_url;

    private String bio;

}
