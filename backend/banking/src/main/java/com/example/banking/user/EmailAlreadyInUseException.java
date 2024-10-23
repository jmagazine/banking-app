package com.example.banking.user;

public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException() {
        super("Email is already in use. Please log in.");
    }

    public EmailAlreadyInUseException(String message) {
        super(message);
    }

    public EmailAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public EmailAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
