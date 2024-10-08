package com.social.socialapi.controller.user;

import com.social.socialapi.dto.request.UserCreationDTO;
import com.social.socialapi.dto.request.UserEditDTO;
import com.social.socialapi.dto.request.UserLoginDTO;
import com.social.socialapi.dto.response.UserProfileViewDTO;
import com.social.socialapi.dto.response.UserViewDTO;
import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.exceptions.UserNotFoundException;
import com.social.socialapi.exceptions.UsernameExistException;
import com.social.socialapi.security.jwt.JwtUntil;
import com.social.socialapi.service.FollowService;
import com.social.socialapi.service.UserService;
import com.social.socialapi.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<UserProfileViewDTO> getUserProfile(@PathVariable int userId, @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        UserViewDTO userViewDTO = userService.getUserViewDTOById(userId);
        int totalFollowers = followService.getTotalNumberOfFollowers(userId);
        int totalFollowings = followService.getTotalNumberOfFollowings(userId);
        int meId = userService.getUserIdByUserDetails(userDetails);
        boolean isFollow = followService.isFollowUser(meId, userId);

        UserProfileViewDTO userProfileViewDTO = new UserProfileViewDTO(userViewDTO, totalFollowers, totalFollowings, isFollow);
        return ResponseEntity.ok(userProfileViewDTO);
    }

    @PutMapping("/user")
    public ResponseEntity<UserViewDTO> editUser(@RequestBody UserEditDTO userEditDTO, @RequestPart MultipartFile file) {
        userEditDTO.setFile(file);
        return ResponseEntity.ok(userService.editUser(userEditDTO));
    }

    @PostMapping("/user/verify")
    public ResponseEntity<UserViewDTO> verifyEmail(String code) throws UsernameExistException, EmailExistException {
        int userId = 1;
        return ResponseEntity.ok(userService.verifyEmail(userId, code));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserViewDTO>> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        int userId = userService.getUserIdByUserDetails(userDetails);
        return ResponseEntity.ok(userService.listAllUserExceptMe(userId));
    }

}
