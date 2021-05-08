package io.ashimjk.strategicaccount.account.application.service;

import io.ashimjk.strategicaccount.account.application.port.in.TransferAmountCommand;
import io.ashimjk.strategicaccount.account.application.port.in.TransferAmountUseCase;
import io.ashimjk.strategicaccount.account.application.port.out.LoadAccountPort;
import io.ashimjk.strategicaccount.account.application.port.out.UpdateAccountStatePort;
import io.ashimjk.strategicaccount.account.common.UseCase;
import io.ashimjk.strategicaccount.account.domain.Account;
import io.ashimjk.strategicaccount.account.domain.AccountNumber;
import io.ashimjk.strategicaccount.account.domain.Amount;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class TransferAmountService implements TransferAmountUseCase {

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean transfer(final TransferAmountCommand command) {

        Account sourceAccount = loadAccountPort.loadAccount(command.getSourceAccountNumber());
        Account targetAccount = loadAccountPort.loadAccount(command.getTargetAccountNumber());
        Amount amount = command.getAmount();

        AccountNumber sourceAccountNumber = sourceAccount.getAccountNumber();
        AccountNumber targetAccountNumber = targetAccount.getAccountNumber();

        sourceAccount.withdraw(targetAccountNumber, amount);
        targetAccount.deposit(sourceAccountNumber, amount);

        updateAccountStatePort.updateAccount(sourceAccount);
        updateAccountStatePort.updateAccount(targetAccount);

        return true;
    }

}
