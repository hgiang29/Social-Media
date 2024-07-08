package com.social.socialapi.service;

import com.social.socialapi.dto.inputdto.UserCreationDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.repository.UserRepository;
import org.springframework.stereotype.Service;

public interface UserService {

    void authenticate(String username, String password) throws Exception;

    UserViewDTO register(UserCreationDTO userCreationDTO) throws EmailExistException;

}
