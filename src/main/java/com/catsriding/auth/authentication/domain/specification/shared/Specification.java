package com.catsriding.auth.authentication.domain.specification.shared;

public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    Specification<T> and(Specification<T> specification);

    Specification<T> or(Specification<T> specification);

}
