package com.example.banking.user;

public class UsernameAlreadyInUseException extends RuntimeException{
    public UsernameAlreadyInUseException() {
        super("Username has been taken.");
    }

    public UsernameAlreadyInUseException(String message) {
        super(message);
    }

    public UsernameAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public UsernameAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
