package io.ashimjk.strategicaccount.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountNumber {

    private static final AccountNumber empty = new AccountNumber("");

    @NonNull private final String value;

    public static AccountNumber of(final String value) {
        return new AccountNumber(value);
    }

    public static AccountNumber empty() {
        return AccountNumber.empty;
    }

}
