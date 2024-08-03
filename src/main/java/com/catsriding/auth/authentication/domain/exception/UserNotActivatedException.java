package com.catsriding.auth.authentication.domain.exception;

import com.catsriding.auth.authentication.domain.exception.shared.DomainException;

public class UserNotActivatedException extends DomainException {

    public UserNotActivatedException() {
    }

    public UserNotActivatedException(String message) {
        super(message);
    }

    public UserNotActivatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
