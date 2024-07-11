package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.UserCreationDTO;
import com.social.socialapi.dto.inputdto.UserEditDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.entity.User;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.exceptions.UsernameExistException;
import com.social.socialapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    void authenticate(String username, String password) throws Exception;

    UserViewDTO getUserDTOByEmail(String email);

    UserViewDTO getUserViewDTOById(int userId);

    UserViewDTO editUser(UserEditDTO userEditDTO);

    UserViewDTO register(UserCreationDTO userCreationDTO) throws EmailExistException, UsernameExistException;

    UserViewDTO verifyEmail(String gmail, String code);

    int getUserIdByUserDetails(UserDetails userDetails);

    List<UserViewDTO> listAllUserExceptMe(int userId);
}
