package com.catsriding.auth.authentication.adapter.input.response.shared;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RestApiStatus {

    SUCCESS(200, "OK", "요청 성공"),
    FAILURE(400, "Bad Request", "잘못된 요청"),

    UNAUTHORIZED(401, "Unauthorized", "인증 필요"),
    FORBIDDEN(403, "Forbidden", "접근 권한 없음"),
    NOT_FOUND(404, "Not Found", "리소스 없음"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "서버 오류"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable", "서비스 사용 불가");

    private final int code;
    private final String phrase;
    private final String description;

}