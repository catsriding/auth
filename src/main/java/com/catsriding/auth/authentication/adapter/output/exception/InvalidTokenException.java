package com.catsriding.auth.authentication.adapter.output.exception;

import com.catsriding.auth.authentication.adapter.output.exception.shared.OutputAdapterException;

public class InvalidTokenException extends OutputAdapterException {

    public InvalidTokenException() {
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
