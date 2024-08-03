package com.catsriding.auth.authentication.application.port.input;

import com.catsriding.auth.authentication.application.model.TokenContext;
import com.catsriding.auth.authentication.application.port.input.command.LoginCommand;
import com.catsriding.auth.common.annotations.UseCase;

@UseCase
public interface LoginUseCase {

    TokenContext login(LoginCommand command);

}
