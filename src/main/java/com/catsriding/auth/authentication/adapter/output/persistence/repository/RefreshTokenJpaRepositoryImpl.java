package com.catsriding.auth.authentication.adapter.output.persistence.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RefreshTokenJpaRepositoryImpl implements RefreshTokenJpaRepositoryExtension {

    private final JPAQueryFactory queryFactory;

}
