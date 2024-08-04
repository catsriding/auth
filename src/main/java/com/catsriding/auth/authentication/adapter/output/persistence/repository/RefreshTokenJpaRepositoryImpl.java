package com.catsriding.auth.authentication.adapter.output.persistence.repository;

import static com.catsriding.auth.authentication.adapter.output.persistence.entity.QRefreshTokenEntity.refreshTokenEntity;
import static com.catsriding.auth.authentication.adapter.output.persistence.entity.QUserEntity.userEntity;

import com.catsriding.auth.authentication.adapter.output.persistence.entity.RefreshTokenEntity;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RefreshTokenJpaRepositoryImpl implements RefreshTokenJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<RefreshTokenEntity> fetchWithUserByUserIdAndDeviceId(UserId userId, String deviceId) {
        return Optional.ofNullable(
                queryFactory
                        .select(refreshTokenEntity)
                        .from(refreshTokenEntity)
                        .innerJoin(refreshTokenEntity.user, userEntity).fetchJoin()
                        .where(
                                userEntity.id.eq(userId.getId()),
                                refreshTokenEntity.deviceId.eq(deviceId)
                        )
                        .orderBy(
                                refreshTokenEntity.id.desc()
                        )
                        .fetchFirst());
    }
}
