package com.catsriding.auth.authentication.adapter.output.persistence;

import com.catsriding.auth.authentication.adapter.output.exception.UserNotFoundException;
import com.catsriding.auth.authentication.adapter.output.persistence.entity.UserEntity;
import com.catsriding.auth.authentication.adapter.output.persistence.repository.UserJpaRepository;
import com.catsriding.auth.authentication.application.port.output.LoadUserPort;
import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.catsriding.auth.common.annotations.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@PersistenceAdapter
public class UserPersistenceAdapter implements LoadUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public User loadForLogin(String username) {
        UserEntity userEntity = userJpaRepository.fetchByUsername(username)
                .orElseThrow(() -> {
                    log.warn("fetchUserByUsername: Does not found user - username={}", username);
                    return new UserNotFoundException("Does not found user by username");
                });
        return userEntity.toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public User loadForRenewAccessToken(UserId userId) {
        UserEntity userEntity = userJpaRepository.fetchActivatedUserById(userId)
                .orElseThrow(() -> {
                    log.warn("fetchByUserId: Does not found user - userId={}", userId.getId());
                    return new UserNotFoundException("Does not found user by userId");
                });
        return userEntity.toDomain();
    }
}
