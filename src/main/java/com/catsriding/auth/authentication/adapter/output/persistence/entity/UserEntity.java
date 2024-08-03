package com.catsriding.auth.authentication.adapter.output.persistence.entity;

import com.catsriding.auth.authentication.domain.entity.User;
import com.catsriding.auth.authentication.domain.vo.UserId;
import com.catsriding.auth.authentication.domain.vo.UserRole;
import com.catsriding.auth.authentication.domain.vo.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 254)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public User toModel() {
        return User.builder()
                .userId(UserId.withId(id))
                .username(username)
                .password(password)
                .role(UserRole.from(role))
                .status(UserStatus.from(status))
                .createdAt(createdAt)
                .build();
    }
}