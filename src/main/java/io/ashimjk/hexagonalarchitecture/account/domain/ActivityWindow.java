package io.ashimjk.hexagonalarchitecture.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityWindow {

    @NonNull private final List<Activity> activities;

    public static ActivityWindow of(@NonNull List<Activity> activities) {
        return new ActivityWindow(activities);
    }

    public static ActivityWindow of(@NonNull Activity... activities) {
        return new ActivityWindow(new ArrayList<>(Arrays.asList(activities)));
    }

    public Amount calculateBalance(AccountNumber accountNumber) {
        Amount depositAmount = activities.stream()
                                         .filter(a -> a.getTargetAccountNumber().equals(accountNumber))
                                         .map(Activity::getAmount)
                                         .reduce(Amount.ZERO, Amount::add);

        Amount withdrawAmount = activities.stream()
                                          .filter(a -> a.getSourceAccountNumber().equals(accountNumber))
                                          .map(Activity::getAmount)
                                          .reduce(Amount.ZERO, Amount::add);

        return Amount.add(depositAmount, withdrawAmount.negate());
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

}
