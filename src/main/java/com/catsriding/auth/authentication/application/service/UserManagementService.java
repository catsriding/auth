package com.catsriding.auth.authentication.application.service;

import com.catsriding.auth.authentication.application.exception.LoginFailedException;
import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.input.LoginUseCase;
import com.catsriding.auth.authentication.application.port.input.RenewAccessTokenUseCase;
import com.catsriding.auth.authentication.application.port.input.command.LoginCommand;
import com.catsriding.auth.authentication.application.port.input.command.RenewAccessTokenCommand;
import com.catsriding.auth.authentication.application.port.output.DecodeTokenPort;
import com.catsriding.auth.authentication.application.port.output.EncryptTokenPort;
import com.catsriding.auth.authentication.application.port.output.GenerateTokenPort;
import com.catsriding.auth.authentication.application.port.output.LoadRefreshTokenPort;
import com.catsriding.auth.authentication.application.port.output.LoadUserPort;
import com.catsriding.auth.authentication.application.port.output.PersistRefreshTokenPort;
import com.catsriding.auth.authentication.application.port.output.VerifyEncryptedDataPort;
import com.catsriding.auth.authentication.domain.entity.RefreshToken;
import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.catsriding.auth.common.annotations.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@UseCase
public class UserManagementService implements LoginUseCase, RenewAccessTokenUseCase {

    private final LoadUserPort loadUserPort;
    private final LoadRefreshTokenPort loadRefreshTokenPort;
    private final VerifyEncryptedDataPort verifyEncryptedDataPort;
    private final GenerateTokenPort generateTokenPort;
    private final DecodeTokenPort decodeTokenPort;
    private final PersistRefreshTokenPort persistRefreshTokenPort;
    private final EncryptTokenPort encryptTokenPort;

    @Override
    public TokenContext login(LoginCommand command) {
        User user = loadUserPort.loadForLogin(command.getUsername());

        verifyPassword(user, command);
        user.checkActivated();

        TokenContext tokenContext = generateTokenPort.generate(user, command.getDeviceId());
        persistRefreshToken(user, tokenContext);

        return tokenContext;
    }

    @Override
    public TokenContext renew(RenewAccessTokenCommand command) {
        UserId userId = decodeTokenPort.claimUserId(command.getRefreshToken());
        RefreshToken refreshToken = loadRefreshTokenPort.loadForRenewAccessToken(userId, command.getDeviceId());
        User user = refreshToken.getUser();

        verifyRefreshToken(refreshToken, command);
        user.checkActivated();
        refreshToken.checkNotExpired();

        TokenContext tokenContext = generateTokenPort.generate(user, command.getDeviceId());
        persistRefreshToken(user, tokenContext);

        return tokenContext;
    }

    private void verifyPassword(User user, LoginCommand command) {
        if (!verifyEncryptedDataPort.verify(command.getPassword(), user.getPassword())) {
            log.warn("verifyPassword: Does not match password - username={}", user.getUsername());
            throw new LoginFailedException("Does not match password.");
        }
    }

    private void verifyRefreshToken(RefreshToken refreshToken, RenewAccessTokenCommand command) {
        if (!verifyEncryptedDataPort.verify(command.getRefreshToken(), refreshToken.getToken())) {
            log.warn("verifyRefreshToken: Does not match refresh token - refreshTokenId={}, deviceId={}",
                    refreshToken.getRefreshTokenId().getId(),
                    refreshToken.getDeviceId());
            throw new LoginFailedException("Does not match refresh token.");
        }
    }

    private void persistRefreshToken(User user, TokenContext tokenContext) {
        String encryptedToken = encryptTokenPort.encrypt(tokenContext.getRefreshToken());
        RefreshToken refreshToken = RefreshToken.from(user, encryptedToken, tokenContext);
        persistRefreshTokenPort.persist(refreshToken);
    }
}
