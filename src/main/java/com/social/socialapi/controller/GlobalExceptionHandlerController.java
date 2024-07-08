package com.social.socialapi.controller;

import com.social.socialapi.exceptions.EmailExistException;
import com.social.socialapi.exceptions.UsernameExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(value = {UsernameExistException.class, EmailExistException.class})
    public ResponseEntity<String> handleUserRegisterException(Exception exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
