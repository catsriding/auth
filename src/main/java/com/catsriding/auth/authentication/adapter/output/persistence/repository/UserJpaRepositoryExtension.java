package com.catsriding.auth.authentication.adapter.output.persistence.repository;

import com.catsriding.auth.authentication.adapter.output.persistence.entity.UserEntity;
import com.catsriding.auth.authentication.domain.vo.UserId;
import java.util.Optional;

public interface UserJpaRepositoryExtension {

    Optional<UserEntity> fetchActivatedUserById(UserId userId);

    Optional<UserEntity> fetchByUsername(String username);

}
