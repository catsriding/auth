package com.catsriding.auth.authentication.domain.specification.shared;

import com.catsriding.auth.authentication.domain.exception.GenericSpecificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> leftSpec;
    private final Specification<T> rightSpec;

    @Override
    public boolean isSatisfiedBy(T t) {
        return leftSpec.isSatisfiedBy(t) || rightSpec.isSatisfiedBy(t);
    }

    @Override
    public void check(T t) throws GenericSpecificationException {

    }
}
