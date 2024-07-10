package com.social.socialapi.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class CommentAddedEvent extends ApplicationEvent {

    private final int senderId;

    private final int commentId;


    public CommentAddedEvent(Object source, int senderId, int commentId) {
        super(source);
        this.senderId = senderId;
        this.commentId = commentId;
    }
}
