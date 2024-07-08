package com.social.socialapi.entity.post;

import com.social.socialapi.dto.inputdto.LikeDTO;
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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Date createdAt;
    @Column(name = "update_at")
    private Date updatedAt;
    public LikeDTO ConvertLikeEntityToDTO(){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(id);
        likeDTO.setUserId(user.getId());
        likeDTO.setPostId(post.getId());
        likeDTO.setLikeUser(user.ConvertEntitytoDTO());
        likeDTO.setPost(post.ConvertPostToPostDTO());
        return likeDTO;
    }
}
