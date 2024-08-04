package com.catsriding.auth.authentication.application.port.output;

import com.catsriding.auth.authentication.domain.vo.UserId;

public interface DecodeTokenPort {

    UserId claimUserId(String refreshToken);

}
