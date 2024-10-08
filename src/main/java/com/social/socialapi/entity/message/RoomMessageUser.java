package com.social.socialapi.entity.message;

import com.social.socialapi.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RoomMessageUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private RoomMessage roomMessage;

    @ManyToOne
    private User user;

    private Date createdAt;


    public RoomMessageUser(RoomMessage roomMessage, User user) {
        this.roomMessage = roomMessage;
        this.user = user;
        this.createdAt = new Date();
    }
}
