package io.ashimjk.hexagonalarchitecture.account.application.port.in;

import io.ashimjk.hexagonalarchitecture.account.domain.Account;

public interface CreateAccountUseCase {

    Account create(CreateAccountCommand createAccountCommand);

}
