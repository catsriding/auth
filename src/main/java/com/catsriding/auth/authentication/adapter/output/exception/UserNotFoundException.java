package com.catsriding.auth.authentication.adapter.output.exception;

import com.catsriding.auth.authentication.adapter.output.exception.shared.OutputAdapterException;

public class UserNotFoundException extends OutputAdapterException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
