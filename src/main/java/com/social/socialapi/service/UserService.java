package com.social.socialapi.service;

import com.social.socialapi.dto.request.UserCreationDTO;
import com.social.socialapi.dto.request.UserEditDTO;
import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.exceptions.UsernameExistException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    void authenticate(String username, String password) throws Exception;

    UserViewDTO getUserDTOByEmail(String email);

    UserViewDTO getUserViewDTOById(int userId);

    UserViewDTO editUser(UserEditDTO userEditDTO);

    UserViewDTO register(UserCreationDTO userCreationDTO) throws EmailExistException, UsernameExistException;

    UserViewDTO verifyEmail(int userId, String code);

    int getUserIdByUserDetails(UserDetails userDetails);

    List<UserViewDTO> listAllUserExceptMe(int userId);
}
