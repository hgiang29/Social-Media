package com.social.socialapi.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private User follower;

    private Date createdAt;

    public Follow(User user, User follower) {
        this.user = user;
        this.follower = follower;
        this.createdAt = new Date();
    }
}
