package com.social.socialapi.controller;

import com.social.socialapi.dto.inputdto.UserCreationDTO;
import com.social.socialapi.dto.inputdto.UserEditDTO;
import com.social.socialapi.dto.inputdto.UserLoginDTO;
import com.social.socialapi.dto.outputdto.UserProfileViewDTO;
import com.social.socialapi.dto.outputdto.UserViewDTO;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.exceptions.UserNotFoundException;
import com.social.socialapi.exceptions.UsernameExistException;
import com.social.socialapi.security.jwt.JwtUntil;
import com.social.socialapi.service.FollowService;
import com.social.socialapi.service.UserService;
import com.social.socialapi.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    JwtUntil jwtUntil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    FollowService followService;


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

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileViewDTO> getUserProfile(@PathVariable int userId) throws UserNotFoundException {
        UserViewDTO userViewDTO = userService.getUserViewDTOById(userId);
        int totalFollowers = followService.getTotalNumberOfFollowers(userId);
        int totalFollowings = followService.getTotalNumberOfFollowings(userId);
        boolean isFollow = followService.isFollowUser(userId);

        UserProfileViewDTO userProfileViewDTO = new UserProfileViewDTO(userViewDTO, totalFollowers, totalFollowings, isFollow);
        return ResponseEntity.ok(userProfileViewDTO);
    }

    @PutMapping("/user")
    public ResponseEntity<UserViewDTO> editUser(@RequestBody UserEditDTO userEditDTO, @RequestPart MultipartFile file) {
        userEditDTO.setFile(file);
        return ResponseEntity.ok(userService.editUser(userEditDTO));
    }


}
