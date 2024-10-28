package com.example.banking.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Customize based on your needs
    public void handleAllExceptions(Exception ex) {
        // Log the exception (using a logging framework)
        System.err.println("An error occurred: " + ex.getMessage());
        // Additional handling if needed
    }
}
