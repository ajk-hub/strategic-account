package io.ashimjk.strategicaccount.account.application.port.out;

import io.ashimjk.strategicaccount.account.domain.Account;

public interface CreateAccountStatePort {

    Account create(Account account);

}
