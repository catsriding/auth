package com.catsriding.auth.authentication.adapter.input.api;

import static com.catsriding.auth.authentication.adapter.input.response.shared.RestApiStatus.SUCCESS;

import com.catsriding.auth.authentication.adapter.input.request.LoginRequest;
import com.catsriding.auth.authentication.adapter.input.response.LoginResponse;
import com.catsriding.auth.authentication.adapter.input.response.shared.RestApiResponse;
import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.input.LoginUseCase;
import com.catsriding.auth.common.annotations.RestApiAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@RestApiAdapter
public class LoginApiAdapter {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<?> loginApi(@Valid @RequestBody LoginRequest request) {
        TokenContext tokenContext = loginUseCase.login(request.toCommand());
        LoginResponse response = LoginResponse.from(tokenContext);
        return ResponseEntity
                .ok(RestApiResponse.compose(SUCCESS, response));
    }

}
