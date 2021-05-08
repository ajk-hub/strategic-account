package io.ashimjk.hexagonalarchitecture.account.application.port.out;

import io.ashimjk.hexagonalarchitecture.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateAccount(Account account);

}
