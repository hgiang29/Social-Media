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
public class RoomMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private User admin;

    private Date createdAt;

    public RoomMessage(String name, User admin) {
        this.name = name;
        this.admin = admin;
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return "RoomMessage{" +
                "name='" + name + '\'' +
                ", admin=" + admin.getLastName() + " " + admin.getFirstName() +
                '}';
    }
}
