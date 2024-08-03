package com.catsriding.auth.authentication.application.port.output;

import com.catsriding.auth.authentication.domain.entity.RefreshToken;

public interface PersistRefreshTokenPort {

    RefreshToken persist(RefreshToken refreshToken);

}
