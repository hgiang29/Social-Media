package com.social.socialapi.entity.message;

import com.social.socialapi.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private RoomMessage roomMessage;

    public Message(User sender, RoomMessage roomMessage, String content) {
        this.sender = sender;
        this.roomMessage = roomMessage;
        this.content = content;
        createdAt = new Date();
    }

    public Message(User sender, RoomMessage roomMessage, String content, Message parentMessage) {
        this.sender = sender;
        this.roomMessage = roomMessage;
        this.content = content;
        this.parentMessage = parentMessage;
        createdAt = new Date();
    }

    private String content;

    @ManyToOne
    private Message parentMessage;

    private Date createdAt;

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender.getFirstName() +
                ", roomMessage=" + roomMessage.getName() +
                ", content='" + content + '\'' +
                '}';
    }
}
