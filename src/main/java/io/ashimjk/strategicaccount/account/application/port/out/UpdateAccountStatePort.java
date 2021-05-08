package io.ashimjk.strategicaccount.account.application.port.out;

import io.ashimjk.strategicaccount.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateAccount(Account account);

}
