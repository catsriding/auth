package com.catsriding.auth.authentication.adapter.output.persistence.entity;

import com.catsriding.auth.authentication.domain.entity.RefreshToken;
import com.catsriding.auth.authentication.domain.vo.RefreshTokenId;
import com.catsriding.auth.authentication.domain.vo.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "token", nullable = false, length = 68)
    private String token;

    @Column(name = "device_id", nullable = false, length = 50)
    private String deviceId;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public static RefreshTokenEntity from(RefreshToken domain, UserEntity userEntity) {
        RefreshTokenEntity entity = new RefreshTokenEntity();
        entity.id = domain.getRefreshTokenId().getId();
        entity.user = userEntity;
        entity.token = domain.getToken();
        entity.deviceId = domain.getDeviceId();
        entity.expiresAt = domain.getExpiresAt();
        entity.createdAt = domain.getCreatedAt();
        return entity;
    }

    public RefreshToken toDomain() {
        return RefreshToken.builder()
                .refreshTokenId(RefreshTokenId.withId(id))
                .userId(UserId.withId(user.getId()))
                .token(token)
                .deviceId(deviceId)
                .expiresAt(expiresAt)
                .createdAt(createdAt)
                .build();
    }
}