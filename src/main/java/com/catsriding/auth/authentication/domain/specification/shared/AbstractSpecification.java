package com.catsriding.auth.authentication.domain.specification.shared;

import com.catsriding.auth.authentication.domain.exception.GenericSpecificationException;

public abstract class AbstractSpecification<T> implements Specification<T> {

    public abstract boolean isSatisfiedBy(T t);

    public abstract void check(T t) throws GenericSpecificationException;

    @Override
    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }

    @Override
    public Specification<T> or(Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

}
