package com.catsriding.auth.authentication.adapter.output.security;

import com.catsriding.auth.authentication.application.port.output.EncryptTokenPort;
import com.catsriding.auth.authentication.application.port.output.VerifyEncryptedDataPort;
import com.catsriding.auth.common.annotations.SecurityAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@SecurityAdapter
public class EncryptionAdapter implements VerifyEncryptedDataPort, EncryptTokenPort {

    private final PasswordEncoder encoder;

    @Override
    public boolean verify(String raw, String encrypted) {
        return encoder.matches(raw, encrypted);
    }

    @Override
    public String encrypt(String token) {
        return encoder.encode(token);
    }
}
