package com.social.socialapi.dto.outputdto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RecentMessageDTO {

    private int roomMessageId;

    private String roomMessageName;

    private UserViewDTO sender;

    private String lastMessageContent;

    private Date createdAt;

}
