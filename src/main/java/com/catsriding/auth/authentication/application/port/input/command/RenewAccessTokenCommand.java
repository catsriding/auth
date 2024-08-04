package com.catsriding.auth.authentication.application.port.input.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class RenewAccessTokenCommand {

    private final String refreshToken;
    private final String deviceId;

}
