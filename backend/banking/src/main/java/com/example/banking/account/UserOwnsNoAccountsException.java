package com.example.banking.account;

public class UserOwnsNoAccountsException extends RuntimeException{
    public UserOwnsNoAccountsException() {
        super("This user does not own any accounts");
    }

    public UserOwnsNoAccountsException(String message) {
        super(message);
    }

    public UserOwnsNoAccountsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserOwnsNoAccountsException(Throwable cause) {
        super(cause);
    }

    public UserOwnsNoAccountsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
