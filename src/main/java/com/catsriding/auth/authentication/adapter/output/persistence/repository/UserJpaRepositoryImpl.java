package com.catsriding.auth.authentication.adapter.output.persistence.repository;

import static com.catsriding.auth.authentication.adapter.output.persistence.entity.QUserEntity.userEntity;

import com.catsriding.auth.authentication.adapter.output.persistence.entity.UserEntity;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.catsriding.auth.authentication.domain.vo.UserStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserJpaRepositoryImpl implements UserJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UserEntity> fetchActivatedUserById(UserId userId) {
        return Optional.ofNullable(
                queryFactory
                        .select(userEntity)
                        .from(userEntity)
                        .where(
                                userEntity.id.eq(userId.getId()),
                                userEntity.status.eq(UserStatus.ACTIVATED.name())
                        )
                        .fetchOne());
    }

    @Override
    public Optional<UserEntity> fetchByUsername(String username) {
        return Optional.ofNullable(
                queryFactory
                        .select(userEntity)
                        .from(userEntity)
                        .where(userEntity.username.eq(username))
                        .fetchOne());
    }
}
