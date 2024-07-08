package com.social.socialapi.dto.outputdto;

import com.social.socialapi.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewDTO {

    private int id;

    private String firstName;

    private String lastName;

    public User ConvertDTOtoEntity(){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }
}
