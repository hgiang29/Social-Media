package com.social.socialapi.entity.post;

import com.social.socialapi.dto.request.ShareDTO;
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
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "share_user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "share_post_id", nullable = true)
    private Post post;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "update_at")
    private Date updateAt;

    public ShareDTO ConvertShareEntityToDTO() {
        ShareDTO ShareDTO = new ShareDTO();
        ShareDTO.setId(id);
        if (user != null) {
            ShareDTO.setShareUser(user.ConvertEntitytoDTO());
            ShareDTO.setUser_id(user.getId());
        }
        ShareDTO.setPostId(post.getId());
        ShareDTO.setCreatedAt(createdAt);
        ShareDTO.setUpdateAt(updateAt);
        return ShareDTO;
    }
}
