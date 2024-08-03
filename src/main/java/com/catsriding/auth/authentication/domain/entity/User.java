package com.catsriding.auth.authentication.domain.entity;

import com.catsriding.auth.authentication.domain.exception.UserNotActivatedException;
import com.catsriding.auth.authentication.domain.specification.UserActivatedSpec;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.catsriding.auth.authentication.domain.vo.UserRole;
import com.catsriding.auth.authentication.domain.vo.UserStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder
@RequiredArgsConstructor
public class User {

    private final UserId userId;
    private final String username;
    private final String password;
    private final UserRole role;
    private final UserStatus status;
    private final LocalDateTime createdAt;

    public void checkActivated() {
        UserActivatedSpec userActivatedSpec = new UserActivatedSpec();

        if (!userActivatedSpec.isSatisfiedBy(this)) {
            log.warn("checkUserActivated: User is not activated - username={}, status={}", username, status);
            throw new UserNotActivatedException("User is not activated.");
        }
    }
}
