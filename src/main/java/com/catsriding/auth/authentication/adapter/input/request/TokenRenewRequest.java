package com.catsriding.auth.authentication.adapter.input.request;

import com.catsriding.auth.authentication.application.port.input.command.RenewAccessTokenCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class TokenRenewRequest {

    @NotBlank
    private final String refreshToken;
    @NotBlank
    @Size(max = 50)
    private final String deviceId;

    public RenewAccessTokenCommand toCommand() {
        return RenewAccessTokenCommand.builder()
                .refreshToken(refreshToken)
                .deviceId(deviceId)
                .build();
    }
}
