package com.catsriding.auth.authentication.adapter.output.persistence.repository;

import com.catsriding.auth.authentication.adapter.output.persistence.entity.RefreshTokenEntity;
import com.catsriding.auth.authentication.domain.vo.UserId;
import java.util.Optional;

public interface RefreshTokenJpaRepositoryExtension {

    Optional<RefreshTokenEntity> fetchWithUserByUserIdAndDeviceId(UserId userId, String deviceId);

}
