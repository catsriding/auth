package com.catsriding.auth.authentication.application.port.output;

public interface VerifyPasswordPort {

    boolean verify(String rawPassword, String encodedPassword);
}
