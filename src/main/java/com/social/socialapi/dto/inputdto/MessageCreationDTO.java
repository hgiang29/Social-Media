package com.social.socialapi.dto.inputdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreationDTO {
    private String messageContent;

    private int senderId;

    private int roomMessageId;

    private int replyMessageId;
}
