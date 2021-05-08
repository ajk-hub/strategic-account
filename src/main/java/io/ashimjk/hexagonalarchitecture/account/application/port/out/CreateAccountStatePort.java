package io.ashimjk.hexagonalarchitecture.account.application.port.out;

import io.ashimjk.hexagonalarchitecture.account.domain.Account;

public interface CreateAccountStatePort {

    Account create(Account account);

}
