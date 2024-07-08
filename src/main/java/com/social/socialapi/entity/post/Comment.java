package com.social.socialapi.entity.post;

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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "comment_user_id", nullable = false)
    private User user;

    private String Content;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Comment parent;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;
}
