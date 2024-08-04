package com.catsriding.auth.authentication.adapter.output.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.catsriding.auth.authentication.adapter.output.exception.InvalidTokenException;
import com.catsriding.auth.authentication.adapter.output.security.props.TokenProperties;
import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.output.DecodeTokenPort;
import com.catsriding.auth.authentication.application.port.output.GenerateTokenPort;
import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.catsriding.auth.common.annotations.SecurityAdapter;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@SecurityAdapter
public class TokenManagementAdapter implements GenerateTokenPort, DecodeTokenPort {

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

    @Override
    public UserId claimUserId(String refreshToken) {
        try {
            Long id = Optional.ofNullable(claim(refreshToken, "id").asLong())
                    .orElseThrow(() -> new InvalidTokenException("Invalid refresh token"));
            return UserId.withId(id);
        } catch (TokenExpiredException e) {
            log.warn("claimUserId: Token has expired.", e);
            throw new InvalidTokenException("Token has expired.");
        } catch (AlgorithmMismatchException e) {
            log.warn("claimUserId: Token algorithm does not match.", e);
            throw new InvalidTokenException("Token algorithm does not match.");
        } catch (SignatureVerificationException e) {
            log.warn("claimUserId: Token signature verification failed.", e);
            throw new InvalidTokenException("Token signature verification failed.");
        } catch (JWTVerificationException e) {
            log.warn("claimUserId: JWT verification failed.", e);
            throw new InvalidTokenException("Token has tampered.");
        }
    }

    private Claim claim(String refreshToken, String key) {
        return JWT.require(algorithm(props.getSecretKey())).build()
                .verify(refreshToken)
                .getClaims()
                .get(key);
    }

}
