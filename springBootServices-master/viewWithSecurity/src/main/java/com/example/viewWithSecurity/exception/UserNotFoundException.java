package com.example.viewWithSecurity.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Convert a predefined exception to an HTTP Status code :optional
//@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Not Found" ,code = HttpStatus.NOT_FOUND)

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
