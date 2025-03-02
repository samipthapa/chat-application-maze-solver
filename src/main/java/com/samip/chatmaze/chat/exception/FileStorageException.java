package com.samip.chatmaze.chat.exception;

import org.springframework.http.HttpStatus;

public class FileStorageException extends GlobalException {

    public static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public FileStorageException(String message) {
        super(message, status);
    }
}
