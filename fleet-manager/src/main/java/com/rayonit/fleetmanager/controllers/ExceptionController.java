package com.rayonit.fleetmanager.controllers;

import com.rayonit.fleetmanager.exceptions.UsernameAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = UsernameAlreadyInUseException.class)
    public ResponseEntity<Object> UAIUexception(UsernameAlreadyInUseException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<Object> BCexception(BadCredentialsException exception) {
        return new ResponseEntity("Bad Credentials", HttpStatus.UNAUTHORIZED);
    }

}
