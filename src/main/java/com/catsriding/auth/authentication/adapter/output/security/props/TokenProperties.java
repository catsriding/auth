package com.catsriding.auth.authentication.adapter.output.security.props;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = TokenProperties.PREFIX)
public class TokenProperties {

    public static final String PREFIX = "auth.token.jwt";

    private String issuer;
    private String secretKey;
    private long expiresAccessToken;
    private long expiresRefreshToken;

}
