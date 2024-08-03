package com.catsriding.auth.authentication.adapter.output.persistence.repository;

import com.catsriding.auth.authentication.adapter.output.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long>, UserJpaRepositoryExtension {

}