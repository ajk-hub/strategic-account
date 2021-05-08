package io.ashimjk.hexagonalarchitecture.account.application.port.in;

import io.ashimjk.hexagonalarchitecture.account.common.SelfValidating;
import io.ashimjk.hexagonalarchitecture.account.domain.AccountNumber;
import io.ashimjk.hexagonalarchitecture.account.domain.Amount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAccountCommand extends SelfValidating<CreateAccountCommand> {

    @NotNull private final AccountNumber accountNumber;
    @NotNull private final Amount amount;

    public static CreateAccountCommand of(AccountNumber accountNumber,
                                          Amount amount) {
        return new CreateAccountCommand(accountNumber, amount).validateSelf();
    }

}
