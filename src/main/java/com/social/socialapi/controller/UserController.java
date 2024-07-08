package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.UserCreationDTO;
import com.social.socialapi.dto.inputdto.UserLoginDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.exceptions.UsernameExistException;
import com.social.socialapi.security.jwt.JwtUntil;
import com.social.socialapi.service.UserService;
import com.social.socialapi.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    JwtUntil jwtUntil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody UserLoginDTO request) throws Exception {

        userService.authenticate(request.getEmail(), request.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getEmail());

        UserViewDTO userViewDTO = userService.getUserDTOByEmail(request.getEmail());
        String token = jwtUntil.generateToken(userDetails);

        return ResponseHandler.generateAuthenticationResponse(userViewDTO, token);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUserAccount(@RequestBody UserCreationDTO userCreationDTO) throws UsernameExistException, EmailExistException {
        return ResponseEntity.ok(userService.register(userCreationDTO));
    }


}
