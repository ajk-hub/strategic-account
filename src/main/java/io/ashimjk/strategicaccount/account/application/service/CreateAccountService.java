package io.ashimjk.strategicaccount.account.application.service;

import io.ashimjk.strategicaccount.account.application.port.in.CreateAccountCommand;
import io.ashimjk.strategicaccount.account.application.port.in.CreateAccountUseCase;
import io.ashimjk.strategicaccount.account.application.port.out.CreateAccountStatePort;
import io.ashimjk.strategicaccount.account.common.UseCase;
import io.ashimjk.strategicaccount.account.domain.Account;
import io.ashimjk.strategicaccount.account.domain.AccountNumber;
import io.ashimjk.strategicaccount.account.domain.Activity;
import io.ashimjk.strategicaccount.account.domain.ActivityWindow;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
class CreateAccountService implements CreateAccountUseCase {

    private final CreateAccountStatePort createAccountStatePort;

    @Override
    public Account create(final CreateAccountCommand createAccountCommand) {

        Activity activity = Activity.of(
                Activity.uniqueReference(),
                createAccountCommand.getAccountNumber(),
                AccountNumber.empty(),
                createAccountCommand.getAccountNumber(),
                createAccountCommand.getAmount(),
                LocalDateTime.now()
        );

        Account account = Account.of(
                createAccountCommand.getAccountNumber(),
                createAccountCommand.getAmount(),
                ActivityWindow.of(activity)
        );

        return createAccountStatePort.create(account);
    }

}
