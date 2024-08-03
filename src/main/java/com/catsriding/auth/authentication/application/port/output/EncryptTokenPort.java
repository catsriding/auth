package com.catsriding.auth.authentication.application.port.output;

public interface EncryptTokenPort {

    String encrypt(String token);

}
