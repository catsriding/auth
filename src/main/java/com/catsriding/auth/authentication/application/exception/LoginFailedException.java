package com.catsriding.auth.authentication.application.exception;

import com.catsriding.auth.authentication.application.exception.shared.ApplicationException;

public class LoginFailedException extends ApplicationException {

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
