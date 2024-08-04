package com.catsriding.auth.authentication.application.port.output;

import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.authentication.domain.vo.UserId;

public interface LoadUserPort {

    User loadForLogin(String username);

    User loadForRenewAccessToken(UserId userId);

}
