package com.catsriding.auth.authentication.domain.entity;

import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.domain.vo.RefreshTokenId;
import com.catsriding.auth.authentication.domain.vo.UserId;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class RefreshToken {

    private final RefreshTokenId refreshTokenId;
    private final UserId userId;
    private final String token;
    private final String deviceId;
    private final LocalDateTime expiresAt;
    private final LocalDateTime createdAt;

    public static RefreshToken from(User user, String encryptedToken, TokenContext tokenContext) {
        return RefreshToken.builder()
                .refreshTokenId(RefreshTokenId.withoutId())
                .userId(user.getUserId())
                .token(encryptedToken)
                .deviceId(tokenContext.getDeviceId())
                .expiresAt(tokenContext.getRefreshTokenExpiresAt())
                .createdAt(tokenContext.getIssuedAt())
                .build();
    }
}
