package com.social.socialapi.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PostAddedEvent extends ApplicationEvent {

    private final int postId;

    private final int senderId;

    public PostAddedEvent(Object source, int postId, int senderId) {
        super(source);
        this.postId = postId;
        this.senderId = senderId;
    }
}