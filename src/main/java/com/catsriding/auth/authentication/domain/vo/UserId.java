package com.catsriding.auth.authentication.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public class UserId {

    private final Long id;

    public static UserId withId(Long id) {
        return new UserId(id);
    }

    public static UserId withoutId() {
        return new UserId(null);
    }

}
