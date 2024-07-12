package com.social.socialapi.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MessageViewDTO {

    private int id;

    private String content;

    private Date createdAt;

    private UserViewDTO sender;

    public MessageViewDTO(int id, String content, Date createdAt, UserViewDTO sender) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.sender = sender;
    }
}
