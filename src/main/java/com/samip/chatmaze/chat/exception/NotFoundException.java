package com.samip.chatmaze.chat.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GlobalException {

  public static final HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message, status);
    }
}
