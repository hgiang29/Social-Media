package com.social.socialapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fullname", length = 50)
    private String fullname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private String gender;

    @Column(name = "email", length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "email_status")
    private String emailStatus;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "user_roles")
    private String userRoles;

    @Column(name = "user_bio", columnDefinition = "TEXT")
    private String userBio;

    @Column(name = "user_status")
    private String userStatus;

    @Column(name = "profile_pic_url", length = 255)
    private String profilePicUrl;

    @Column(name = "address", length = 255)
    private String address;
}