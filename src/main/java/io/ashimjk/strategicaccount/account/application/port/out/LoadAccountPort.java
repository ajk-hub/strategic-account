package io.ashimjk.strategicaccount.account.application.port.out;

import io.ashimjk.strategicaccount.account.domain.Account;
import io.ashimjk.strategicaccount.account.domain.AccountNumber;

public interface LoadAccountPort {

    Account loadAccount(AccountNumber accountNumber);

}
