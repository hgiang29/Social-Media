package com.social.socialapi.dto.response;

import com.social.socialapi.entity.user.User;
import com.social.socialapi.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String profile_pic_url;

    private String username;

    private String bio;

    private Gender gender;

    private String email;

    public User ConvertDTOtoEntity() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setProfile_pic_url(profile_pic_url);
        return user;
    }

}
