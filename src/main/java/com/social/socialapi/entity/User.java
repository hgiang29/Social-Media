package com.social.socialapi.entity;

import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.enums.EmailStatus;
import com.social.socialapi.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;

    private String username;

    private String password;

    private String bio;

    private String profile_pic_url;

    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private EmailStatus emailStatus;

    public User(String firstName, String lastName, Gender gender, String email, String username, String password, String bio, String profile_pic_url) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.profile_pic_url = profile_pic_url;
        this.createdAt = new Date();
        emailStatus = EmailStatus.Unverified;
    }

    public User(String firstName, String lastName, Gender gender, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdAt = new Date();
        emailStatus = EmailStatus.Unverified;
    }

    public User(String firstName, String lastName, Gender gender, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.createdAt = new Date();
        emailStatus = EmailStatus.Unverified;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public UserViewDTO ConvertEntitytoDTO(){
        UserViewDTO userDTO = new UserViewDTO();
        userDTO.setId(id);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        return userDTO;
    }
}