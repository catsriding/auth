package com.catsriding.auth.authentication.application.service;

import com.catsriding.auth.authentication.application.exception.LoginFailedException;
import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.input.LoginUseCase;
import com.catsriding.auth.authentication.application.port.input.command.LoginCommand;
import com.catsriding.auth.authentication.application.port.output.EncryptTokenPort;
import com.catsriding.auth.authentication.application.port.output.GenerateTokenPort;
import com.catsriding.auth.authentication.application.port.output.LoadUserPort;
import com.catsriding.auth.authentication.application.port.output.PersistRefreshTokenPort;
import com.catsriding.auth.authentication.application.port.output.VerifyPasswordPort;
import com.catsriding.auth.authentication.domain.entity.RefreshToken;
import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.common.annotations.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@UseCase
public class UserManagementService implements LoginUseCase {

    private final LoadUserPort loadUserPort;
    private final VerifyPasswordPort verifyPasswordPort;
    private final GenerateTokenPort generateTokenPort;
    private final PersistRefreshTokenPort persistRefreshTokenPort;
    private final EncryptTokenPort encryptTokenPort;

    @Override
    public TokenContext login(LoginCommand command) {
        User user = loadUserPort.fetchUserByUsername(command.getUsername());

        verifyPassword(user, command);
        user.checkActivated();

        TokenContext tokenContext = generateTokenPort.generate(user, command.getDeviceId());
        String encryptedToken = encryptTokenPort.encrypt(tokenContext.getRefreshToken());
        RefreshToken refreshToken = RefreshToken.from(user, encryptedToken, tokenContext);
        persistRefreshTokenPort.persist(refreshToken);

        return tokenContext;
    }

    private void verifyPassword(User user, LoginCommand command) {
        if (!verifyPasswordPort.verify(command.getPassword(), user.getPassword())) {
            log.warn("verifyPassword: Does not match password - username={}", user.getUsername());
            throw new LoginFailedException("Does not match password.");
        }
    }

}
