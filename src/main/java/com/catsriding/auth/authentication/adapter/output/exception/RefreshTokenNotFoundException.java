package com.catsriding.auth.authentication.adapter.output.exception;

import com.catsriding.auth.authentication.adapter.output.exception.shared.OutputAdapterException;

public class RefreshTokenNotFoundException extends OutputAdapterException {

    public RefreshTokenNotFoundException() {
    }

    public RefreshTokenNotFoundException(String message) {
        super(message);
    }

    public RefreshTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
