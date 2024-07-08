package com.social.socialapi.dto.outputdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileViewDTO {

    private UserViewDTO userInfo;

    private int totalFollowers;

    private int totalFollowings;

    public UserProfileViewDTO(UserViewDTO userInfo, int totalFollowers, int totalFollowings) {
        this.userInfo = userInfo;
        this.totalFollowers = totalFollowers;
        this.totalFollowings = totalFollowings;
    }
}
