package com.social.socialapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.socialapi.dto.inputdto.UserCreationRequest;
import com.social.socialapi.dto.outputdto.ApiResponse;
import com.social.socialapi.dto.outputdto.UserResponse;

import jakarta.validation.Valid;

// import com.social.socialapi.service.UserService;
@RestController
@RequestMapping("/users")
public class UserController {
    // private UserService userService;
    @PostMapping
    private ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        System.out.println("User created successfully");
        return ApiResponse.<UserResponse>builder()
                .result(UserResponse.builder()
                        .id("1")
                        .fullname(request.getFullname())
                        .email(request.getEmail())
                        .build())
                .build();
    }
}
