package io.ashimjk.strategicaccount.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Activity {

    @NonNull private final String reference;
    @NonNull private final AccountNumber ownerAccountNumber;
    @NonNull private final AccountNumber sourceAccountNumber;
    @NonNull private final AccountNumber targetAccountNumber;
    @NonNull private final Amount amount;
    @NonNull private final LocalDateTime transactionDate;

    public static String uniqueReference() {
        return UUID.randomUUID().toString();
    }

    public static Activity of(
            final String reference,
            final AccountNumber ownerAccountNumber,
            final AccountNumber sourceAccountNumber,
            final AccountNumber targetAccountNumber,
            final Amount amount,
            final LocalDateTime transactionDate) {

        return new Activity(
                reference,
                ownerAccountNumber,
                sourceAccountNumber,
                targetAccountNumber,
                amount,
                transactionDate
        );
    }

}
