package com.catsriding.auth.authentication.domain.entity;

import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.domain.exception.TokenHasExpiredException;
import com.catsriding.auth.authentication.domain.specification.TokenNotExpiredSpec;
import com.catsriding.auth.authentication.domain.vo.RefreshTokenId;
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
    private final User user;
    private final String token;
    private final String deviceId;
    private final LocalDateTime expiresAt;
    private final LocalDateTime createdAt;

    public static RefreshToken from(User user, String encryptedToken, TokenContext tokenContext) {
        return RefreshToken.builder()
                .refreshTokenId(RefreshTokenId.withoutId())
                .user(user)
                .token(encryptedToken)
                .deviceId(tokenContext.getDeviceId())
                .expiresAt(tokenContext.getRefreshTokenExpiresAt())
                .createdAt(tokenContext.getIssuedAt())
                .build();
    }

    public void checkNotExpired() {
        TokenNotExpiredSpec tokenNotExpiredSpec = new TokenNotExpiredSpec();

        if (!tokenNotExpiredSpec.isSatisfiedBy(this)) {
            log.warn("checkNotExpired: Token has expired - refreshTokenId={}, expiresAt={}",
                    refreshTokenId.getId(),
                    expiresAt);
            throw new TokenHasExpiredException("Token has expired.");
        }
    }
}
