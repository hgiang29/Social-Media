package com.social.socialapi.entity.message;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LiveMessage {
    private String senderName;
    private String receiverName;
    private String message;
}