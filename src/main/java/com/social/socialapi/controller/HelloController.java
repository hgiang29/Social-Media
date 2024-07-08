package com.social.socialapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hehe");
    }

    @GetMapping("/jwt")
    public ResponseEntity<String> jwtcheck() {
        return ResponseEntity.ok("jwt works properly");
    }


}
