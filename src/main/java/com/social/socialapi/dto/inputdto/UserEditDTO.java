package com.social.socialapi.dto.inputdto;

import com.social.socialapi.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDTO {

    private int id;

    private String firstName;

    private String lastName;

    private Gender gender;

    private String bio;

    private MultipartFile file;
}
