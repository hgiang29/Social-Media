package com.social.socialapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.socialapi.dto.outputdto.ApiResponse;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public  ResponseEntity<String> hello() {
        return ResponseEntity.ok("hehe");
    }


    @GetMapping("/hello2")
    public ApiResponse<String> hello2() {
        return ApiResponse.<String>builder().message("hehe").build();
    }

}
