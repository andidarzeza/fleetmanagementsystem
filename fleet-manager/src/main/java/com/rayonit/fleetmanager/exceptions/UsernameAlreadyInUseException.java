package com.rayonit.fleetmanager.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyInUseException extends RuntimeException{
    private String message;
    private HttpStatus status;

    public UsernameAlreadyInUseException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
