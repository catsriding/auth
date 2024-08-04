package com.catsriding.auth.authentication.domain.exception;

import com.catsriding.auth.authentication.domain.exception.shared.DomainException;

public class TokenHasExpiredException extends DomainException {

    public TokenHasExpiredException() {
    }

    public TokenHasExpiredException(String message) {
        super(message);
    }

    public TokenHasExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
