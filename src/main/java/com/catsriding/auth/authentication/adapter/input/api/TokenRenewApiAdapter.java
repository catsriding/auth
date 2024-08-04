package com.catsriding.auth.authentication.adapter.input.api;

import static com.catsriding.auth.authentication.adapter.input.response.shared.RestApiStatus.SUCCESS;

import com.catsriding.auth.authentication.adapter.input.request.TokenRenewRequest;
import com.catsriding.auth.authentication.adapter.input.response.TokenRenewResponse;
import com.catsriding.auth.authentication.adapter.input.response.shared.RestApiResponse;
import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.input.RenewAccessTokenUseCase;
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
public class TokenRenewApiAdapter {

    private final RenewAccessTokenUseCase renewAccessTokenUseCase;

    @PostMapping("/renew")
    public ResponseEntity<?> renew(@Valid @RequestBody TokenRenewRequest request) {
        TokenContext tokenContext = renewAccessTokenUseCase.renew(request.toCommand());
        TokenRenewResponse response = TokenRenewResponse.from(tokenContext);
        return ResponseEntity
                .ok(RestApiResponse.compose(SUCCESS, response));
    }

}
