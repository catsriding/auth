package com.catsriding.auth.authentication.adapter.input.response.shared;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RestApiResponse<T> {

    private final int code;
    private final String phrase;
    private final T payload;

    @Builder
    public RestApiResponse(int code, String phrase, T payload) {
        this.code = code;
        this.phrase = phrase;
        this.payload = payload;
    }

    public static <DATA> RestApiResponse<?> compose(RestApiStatus status, DATA payload) {
        return RestApiResponse.builder()
                .code(status.getCode())
                .phrase(status.getPhrase())
                .payload(payload)
                .build();
    }

    public static RestApiResponse<?> compose(RestApiStatus status, String phrase) {
        return RestApiResponse.builder()
                .code(status.getCode())
                .phrase(phrase)
                .build();
    }

}
