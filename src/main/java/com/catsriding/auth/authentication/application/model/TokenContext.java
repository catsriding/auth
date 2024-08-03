package com.catsriding.auth.authentication.application.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class TokenContext {

    private final String tokenType;
    private final String deviceId;
    private final String accessToken;
    private final LocalDateTime accessTokenExpiresAt;
    private final String refreshToken;
    private final LocalDateTime refreshTokenExpiresAt;
    private final LocalDateTime issuedAt;

    public TokenContext(
            String deviceId,
            String accessToken,
            Instant accessTokenExpiresAt,
            String refreshToken,
            Instant refreshTokenExpiresAt,
            Instant issuedAt) {
        this.tokenType = "bearer";
        this.deviceId = deviceId;
        this.accessToken = accessToken;
        this.accessTokenExpiresAt = convert(accessTokenExpiresAt);
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresAt = convert(refreshTokenExpiresAt);
        this.issuedAt = convert(issuedAt);
    }

    private static LocalDateTime convert(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
