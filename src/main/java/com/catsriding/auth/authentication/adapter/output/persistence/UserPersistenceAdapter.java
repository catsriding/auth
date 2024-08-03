package com.catsriding.auth.authentication.adapter.output.persistence;

import com.catsriding.auth.authentication.adapter.output.exception.UserNotFoundException;
import com.catsriding.auth.authentication.adapter.output.persistence.entity.UserEntity;
import com.catsriding.auth.authentication.adapter.output.persistence.repository.UserJpaRepository;
import com.catsriding.auth.authentication.application.port.output.LoadUserPort;
import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.common.annotations.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@PersistenceAdapter
public class UserPersistenceAdapter implements LoadUserPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User fetchUserByUsername(String username) {
        UserEntity userEntity = userJpaRepository.fetchByUsername(username)
                .orElseThrow(() -> {
                    log.warn("fetchUserByUsername: Does not found user - username={}", username);
                    return new UserNotFoundException("Does not found user by username");
                });
        return userEntity.toModel();
    }
}
