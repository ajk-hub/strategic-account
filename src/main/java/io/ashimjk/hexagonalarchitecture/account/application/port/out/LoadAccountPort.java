package io.ashimjk.hexagonalarchitecture.account.application.port.out;

import io.ashimjk.hexagonalarchitecture.account.domain.Account;
import io.ashimjk.hexagonalarchitecture.account.domain.AccountNumber;

public interface LoadAccountPort {

    Account loadAccount(AccountNumber accountNumber);

}
