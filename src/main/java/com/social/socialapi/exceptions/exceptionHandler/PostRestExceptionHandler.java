package com.social.socialapi.exceptions.exceptionHandler;

import com.social.socialapi.dto.response.PostErrorReponse;
import com.social.socialapi.exceptions.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PostRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<PostErrorReponse> handleException(PostNotFoundException e) {
        PostErrorReponse error = new PostErrorReponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PostErrorReponse> handleException(Exception e) {
        // Exception nao bi throw ra thi nhay luon vao day
        PostErrorReponse error = new PostErrorReponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
