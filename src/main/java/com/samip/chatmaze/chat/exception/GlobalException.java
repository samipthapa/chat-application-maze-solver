package com.samip.chatmaze.chat.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {
    
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public GlobalException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public GlobalException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
