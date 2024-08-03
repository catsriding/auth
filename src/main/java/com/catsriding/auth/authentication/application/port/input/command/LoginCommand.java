package com.catsriding.auth.authentication.application.port.input.command;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class LoginCommand {

    private final String username;
    private final String password;
    private final String deviceId;

}
