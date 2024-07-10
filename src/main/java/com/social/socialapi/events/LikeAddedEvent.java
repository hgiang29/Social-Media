package com.social.socialapi.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class LikeAddedEvent extends ApplicationEvent {

    private final int likeId;

    private final int senderId;

    public LikeAddedEvent(Object source, int likeId, int senderId) {
        super(source);
        this.likeId = likeId;
        this.senderId = senderId;
    }
}
