package com.catsriding.auth.authentication.application.port.output;

public interface VerifyEncryptedDataPort {

    boolean verify(String raw, String encrypted);
}
