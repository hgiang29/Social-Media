package com.social.socialapi.entity.message;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LiveMessage {
    private int senderId;
    private int roomMessageId;
    private String messageContent;
}