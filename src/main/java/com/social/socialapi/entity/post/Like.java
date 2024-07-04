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
@Table(name = "`like`")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;

    //    @ManyToOne
//    private User user;
    private Date createdAt;
    @Column(name="update_at")
    private Date updatedAt;

}
