package io.ashimjk.strategicaccount.account.application.port.in;

import io.ashimjk.strategicaccount.account.common.SelfValidating;
import io.ashimjk.strategicaccount.account.domain.AccountNumber;
import io.ashimjk.strategicaccount.account.domain.Amount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferAmountCommand extends SelfValidating<TransferAmountCommand> {

    @NotNull private final AccountNumber sourceAccountNumber;
    @NotNull private final AccountNumber targetAccountNumber;
    @NotNull private final Amount amount;

    public static TransferAmountCommand of(AccountNumber sourceAccountNumber, AccountNumber targetAccountNumber, Amount amount) {
        return new TransferAmountCommand(sourceAccountNumber, targetAccountNumber, amount).validateSelf();
    }

}
