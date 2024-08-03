package com.catsriding.auth.authentication.domain.vo;

import java.util.Arrays;

public enum UserStatus {

    ACTIVATED,
    DEACTIVATED;

    public static UserStatus from(String name) {
        return Arrays.stream(UserStatus.values())
                .filter(status -> status.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""));
    }
}
