package com.social.socialapi.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class FollowedEvent extends ApplicationEvent {

    private final int followId;

    private final int senderId;

    public FollowedEvent(Object source, int followId, int senderId) {
        super(source);
        this.followId = followId;
        this.senderId = senderId;
    }
}
