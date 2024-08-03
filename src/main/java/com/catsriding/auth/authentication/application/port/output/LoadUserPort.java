package com.catsriding.auth.authentication.application.port.output;

import com.catsriding.auth.authentication.domain.entity.User;

public interface LoadUserPort {

    User fetchUserByUsername(String username);
}
