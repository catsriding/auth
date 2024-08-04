package com.catsriding.auth.authentication.application.port.input;

import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.input.command.RenewAccessTokenCommand;

public interface RenewAccessTokenUseCase {

    TokenContext renew(RenewAccessTokenCommand command);

}
