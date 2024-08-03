package com.catsriding.auth.authentication.adapter.input.request;

import com.catsriding.auth.authentication.application.port.input.command.LoginCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class LoginRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]{1,64}@[a-zA-Z0-9.-]{1,255}\\.[a-zA-Z]{2,}$")
    @Size(max = 254)
    private final String username;
    @NotBlank
    @Size(max = 100)
    private final String password;
    @NotBlank
    @Size(max = 50)
    private final String deviceId;

    public LoginCommand toCommand() {
        return LoginCommand.builder()
                .username(username)
                .password(password)
                .deviceId(deviceId)
                .build();
    }
}
