package com.catsriding.auth.authentication.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class RefreshTokenId {

    private final Long id;

    public static RefreshTokenId withId(Long id) {
        return new RefreshTokenId(id);
    }

    public static RefreshTokenId withoutId() {
        return new RefreshTokenId(null);
    }
}
