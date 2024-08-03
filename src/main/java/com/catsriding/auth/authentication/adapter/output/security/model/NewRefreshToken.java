package com.catsriding.auth.authentication.adapter.output.security.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class NewRefreshToken {

    private final String token;
    private final LocalDateTime expiresAt;

}
