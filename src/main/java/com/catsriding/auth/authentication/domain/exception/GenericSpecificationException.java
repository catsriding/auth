package com.catsriding.auth.authentication.domain.exception;

import com.catsriding.auth.authentication.domain.exception.shared.DomainException;

public class GenericSpecificationException extends DomainException {

    public GenericSpecificationException() {
    }

    public GenericSpecificationException(String message) {
        super(message);
    }

    public GenericSpecificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
