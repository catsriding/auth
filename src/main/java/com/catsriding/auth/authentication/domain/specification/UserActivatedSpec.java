package com.catsriding.auth.authentication.domain.specification;

import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.authentication.domain.exception.GenericSpecificationException;
import com.catsriding.auth.authentication.domain.specification.shared.AbstractSpecification;
import com.catsriding.auth.authentication.domain.vo.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserActivatedSpec extends AbstractSpecification<User> {

    @Override
    public boolean isSatisfiedBy(User user) {
        return user != null && isUserActivated(user);
    }

    @Override
    public void check(User user) throws GenericSpecificationException {

    }

    private static boolean isUserActivated(User user) {
        return user.getStatus().equals(UserStatus.ACTIVATED);
    }
}
