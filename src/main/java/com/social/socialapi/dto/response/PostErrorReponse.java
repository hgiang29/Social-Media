package com.social.socialapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostErrorReponse {
    private int status;
    private String message;
    private long timestamp;

    public PostErrorReponse() {
    }

    public PostErrorReponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
