package com.catsriding.auth.authentication.application.port.output;

import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.domain.entity.User;

public interface GenerateTokenPort {

    TokenContext generate(User user, String deviceId);

}
