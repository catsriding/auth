package com.catsriding.auth.authentication.adapter.output.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.catsriding.auth.authentication.adapter.output.security.props.TokenProperties;
import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.output.GenerateTokenPort;
import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.common.annotations.SecurityAdapter;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SecurityAdapter
public class TokenManagementAdapter implements GenerateTokenPort {

    private final TokenProperties props;

    @Override
    public TokenContext generate(User user, String deviceId) {
        long currentTimeMillis = System.currentTimeMillis();
        Instant issuedAt = issuedAt(currentTimeMillis);
        Instant accessTokenExpires = expiresAt(currentTimeMillis, props.getExpiresAccessToken());
        Instant refreshTokenExpires = expiresAt(currentTimeMillis, props.getExpiresRefreshToken());
        String accessToken = generateAccessToken(user, issuedAt, accessTokenExpires);
        String refreshToken = generateRefreshToken(user, issuedAt, refreshTokenExpires);

        log.info("generate: Successfully created token = userId={}, deviceId={}, issuedAt={}, refreshTokenExpires={}",
                user.getUserId().getId(),
                deviceId,
                issuedAt,
                refreshTokenExpires);

        return new TokenContext(
                deviceId,
                accessToken,
                accessTokenExpires,
                refreshToken,
                refreshTokenExpires,
                issuedAt);
    }

    private String generateAccessToken(User user, Instant issuedAt, Instant expiresAt) {
        return JWT.create()
                .withClaim("id", user.getUserId().getId())
                .withClaim("username", user.getUsername())
                .withClaim("roles", user.getRole().name())
                .withIssuer(props.getIssuer())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(algorithm(props.getSecretKey()));
    }

    private String generateRefreshToken(User user, Instant issuedAt, Instant expiresAt) {
        return JWT.create()
                .withClaim("id", user.getUserId().getId())
                .withIssuer(props.getIssuer())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(algorithm(props.getSecretKey()));
    }

    private Algorithm algorithm(String secretKey) {
        return Algorithm.HMAC256(secretKey);
    }

    private Instant issuedAt(long currentTimeMillis) {
        return Instant.ofEpochMilli(currentTimeMillis);
    }

    private Instant expiresAt(long currentTimeMillis, long expires) {
        return Instant.ofEpochMilli(currentTimeMillis + expires);
    }
}
