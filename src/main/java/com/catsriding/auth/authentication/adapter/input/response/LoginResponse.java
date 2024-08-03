package com.catsriding.auth.authentication.adapter.input.response;

import com.catsriding.auth.authentication.application.model.TokenContext;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class LoginResponse {

    private final String tokenType;
    private final String accessToken;
    private final LocalDateTime accessTokenExpiresAt;
    private final String refreshToken;
    private final LocalDateTime refreshTokenExpiresAt;

    public static LoginResponse from(TokenContext tokenContext) {
        return LoginResponse.builder()
                .tokenType(tokenContext.getTokenType())
                .accessToken(tokenContext.getAccessToken())
                .accessTokenExpiresAt(tokenContext.getAccessTokenExpiresAt())
                .refreshToken(tokenContext.getRefreshToken())
                .refreshTokenExpiresAt(tokenContext.getRefreshTokenExpiresAt())
                .build();
    }
}
