package com.catsriding.auth.authentication.adapter.output.persistence;

import com.catsriding.auth.authentication.adapter.output.exception.UserNotFoundException;
import com.catsriding.auth.authentication.adapter.output.persistence.entity.RefreshTokenEntity;
import com.catsriding.auth.authentication.adapter.output.persistence.entity.UserEntity;
import com.catsriding.auth.authentication.adapter.output.persistence.repository.RefreshTokenJpaRepository;
import com.catsriding.auth.authentication.adapter.output.persistence.repository.UserJpaRepository;
import com.catsriding.auth.authentication.application.port.output.PersistRefreshTokenPort;
import com.catsriding.auth.authentication.domain.entity.RefreshToken;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.catsriding.auth.common.annotations.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@PersistenceAdapter
public class RefreshTokenPersistenceAdapter implements PersistRefreshTokenPort {

    private final UserJpaRepository userJpaRepository;
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    @Transactional
    public RefreshToken persist(RefreshToken refreshToken) {
        UserEntity userEntity = loadUserEntity(refreshToken.getUserId());
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.from(refreshToken, userEntity);

        refreshTokenEntity = refreshTokenJpaRepository.save(refreshTokenEntity);

        log.info("persist: Successfully persisted new refresh token: userId={}, refreshTokenId={}",
                userEntity.getId(),
                refreshTokenEntity.getId());

        return refreshTokenEntity.toDomain();
    }

    private UserEntity loadUserEntity(UserId userId) {
        return userJpaRepository.fetchActivatedUserById(userId)
                .orElseThrow(() -> {
                    log.warn("persist: Does not found activated userEntity - userId={}", userId.getId());
                    return new UserNotFoundException("Does not found user by userId");
                });
    }
}
