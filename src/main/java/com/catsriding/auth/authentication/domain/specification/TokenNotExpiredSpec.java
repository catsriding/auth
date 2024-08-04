package com.catsriding.auth.authentication.domain.specification;

import com.catsriding.auth.authentication.domain.entity.RefreshToken;
import com.catsriding.auth.authentication.domain.exception.GenericSpecificationException;
import com.catsriding.auth.authentication.domain.specification.shared.AbstractSpecification;
import java.time.LocalDateTime;

public class TokenNotExpiredSpec extends AbstractSpecification<RefreshToken> {

    @Override
    public boolean isSatisfiedBy(RefreshToken refreshToken) {
        return refreshToken != null && refreshToken.getExpiresAt().isAfter(LocalDateTime.now());
    }

    @Override
    public void check(RefreshToken refreshToken) throws GenericSpecificationException {

    }
}
