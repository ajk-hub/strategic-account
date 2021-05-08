package io.ashimjk.strategicaccount.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    @NonNull private final AccountNumber accountNumber;
    @NonNull private final Amount baselineAmount;
    @NonNull private final ActivityWindow activityWindow;

    public static Account of(AccountNumber accountNumber, Amount baselineAmount, ActivityWindow activityWindow) {
        return new Account(accountNumber, baselineAmount, activityWindow);
    }

    public void withdraw(final AccountNumber targetAccountNumber, final Amount amount) {
        Activity activity = Activity.of(
                Activity.uniqueReference(),
                this.accountNumber,
                this.accountNumber,
                targetAccountNumber,
                amount,
                LocalDateTime.now()
        );

        this.activityWindow.addActivity(activity);
    }

    public void deposit(final AccountNumber sourceAccountNumber, final Amount amount) {
        Activity activity = Activity.of(
                Activity.uniqueReference(),
                this.accountNumber,
                sourceAccountNumber,
                this.accountNumber,
                amount,
                LocalDateTime.now()
        );

        this.activityWindow.addActivity(activity);
    }

}
