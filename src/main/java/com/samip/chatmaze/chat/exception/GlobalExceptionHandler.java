package com.samip.chatmaze.chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(GlobalException exc) {
        ErrorResponse error = new ErrorResponse();

        error.setMessage(exc.getMessage());
        error.setStatus(exc.getStatus().value());
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, exc.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        ErrorResponse error = new ErrorResponse();

        error.setMessage(exc.getMessage());
        error.setStatus(500);
        error.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
