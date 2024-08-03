package com.catsriding.auth.authentication.adapter.output.security;

import com.catsriding.auth.authentication.application.port.output.EncryptTokenPort;
import com.catsriding.auth.authentication.application.port.output.VerifyPasswordPort;
import com.catsriding.auth.common.annotations.SecurityAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@SecurityAdapter
public class EncryptionAdapter implements VerifyPasswordPort, EncryptTokenPort {

    private final PasswordEncoder encoder;

    @Override
    public boolean verify(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public String encrypt(String token) {
        return encoder.encode(token);
    }
}
