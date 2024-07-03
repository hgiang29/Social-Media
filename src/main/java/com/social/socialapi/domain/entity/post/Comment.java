package com.social.socialapi.domain.entity.post;

import com.social.socialapi.domain.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private int Id;

    @ManyToOne
    private User user;

    private String Content;

    private Date createdAt;

    @ManyToOne
    private Comment parentComment;


}
