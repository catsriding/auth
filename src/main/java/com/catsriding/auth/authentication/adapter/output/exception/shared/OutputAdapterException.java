package com.catsriding.auth.authentication.adapter.output.exception.shared;

public class OutputAdapterException extends RuntimeException {

    public OutputAdapterException() {
    }

    public OutputAdapterException(String message) {
        super(message);
    }

    public OutputAdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutputAdapterException(Throwable cause) {
        super(cause);
    }

    public OutputAdapterException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
