package com.social.socialapi.dto.inputdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageCreationDTO {
    private String messageContent;

    private int senderId;

    private int roomMessageId;

    private int replyMessageId;

    public MessageCreationDTO(String messageContent, int senderId, int roomMessageId) {
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.roomMessageId = roomMessageId;
    }
}
