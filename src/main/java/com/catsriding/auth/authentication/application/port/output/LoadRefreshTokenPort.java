package com.catsriding.auth.authentication.application.port.output;

import com.catsriding.auth.authentication.domain.entity.RefreshToken;
import com.catsriding.auth.authentication.domain.vo.UserId;

public interface LoadRefreshTokenPort {

    RefreshToken loadForRenewAccessToken(UserId userId, String deviceId);

}
