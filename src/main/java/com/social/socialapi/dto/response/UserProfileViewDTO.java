package com.social.socialapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileViewDTO {

    private UserViewDTO userInfo;

    private int totalFollowers;

    private int totalFollowings;

    private boolean isFollow;

    public UserProfileViewDTO(UserViewDTO userInfo, int totalFollowers, int totalFollowings, boolean isFollow) {
        this.userInfo = userInfo;
        this.totalFollowers = totalFollowers;
        this.totalFollowings = totalFollowings;
        this.isFollow = isFollow;
    }
}
