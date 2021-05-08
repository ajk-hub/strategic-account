package io.ashimjk.strategicaccount.account.application.port.in;

import io.ashimjk.strategicaccount.account.domain.Account;

public interface CreateAccountUseCase {

    Account create(CreateAccountCommand createAccountCommand);

}
