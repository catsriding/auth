package com.catsriding.auth.authentication.domain.vo;

import java.util.Arrays;

public enum UserRole {

    ROLE_GUEST,
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_DEV;

    public static UserRole from(String name) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""));
    }
}
