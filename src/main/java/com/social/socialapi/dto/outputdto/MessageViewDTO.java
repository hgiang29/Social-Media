package com.social.socialapi.dto.outputdto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageViewDTO {

    private int id;

    private String content;

    private Date createdAt;

    private UserViewDTO sender;

}
