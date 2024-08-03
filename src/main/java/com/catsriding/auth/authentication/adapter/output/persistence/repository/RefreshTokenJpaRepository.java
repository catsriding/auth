package com.catsriding.auth.authentication.adapter.output.persistence.repository;

import com.catsriding.auth.authentication.adapter.output.persistence.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends
        JpaRepository<RefreshTokenEntity, Long>,
        RefreshTokenJpaRepositoryExtension {

}