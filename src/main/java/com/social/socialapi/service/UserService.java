package com.social.socialapi.service;

import com.social.socialapi.repository.UserRepository;
import org.springframework.stereotype.Service;

public interface UserService {

    public void authenticate(String username, String password) throws Exception;

}
