package io.ashimjk.hexagonalarchitecture.account.adapter.out.persistence;

import io.ashimjk.hexagonalarchitecture.account.domain.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
class AccountMapper {

    Account mapToDomainEntity(final AccountEntity accountEntity,
                              final List<ActivityEntity> activityEntities,
                              final BigDecimal withdrawalBalance,
                              final BigDecimal depositBalance) {

        Amount baselineBalance = Amount.subtract(Amount.of(depositBalance), Amount.of(withdrawalBalance));

        return Account.of(AccountNumber.of(accountEntity.getAccountNumber()),
                          baselineBalance,
                          mapToActivityWindow(activityEntities));
    }

    private ActivityWindow mapToActivityWindow(final List<ActivityEntity> activityEntities) {
        List<Activity> mappedActivities = new ArrayList<>();

        for (ActivityEntity activityEntity : activityEntities) {
            mappedActivities.add(Activity.of(
                    activityEntity.getReference(),
                    AccountNumber.of(activityEntity.getOwnerAccountNumber()),
                    AccountNumber.of(activityEntity.getSourceAccountNumber()),
                    AccountNumber.of(activityEntity.getTargetAccountNumber()),
                    Amount.of(activityEntity.getAmount()),
                    activityEntity.getTransactionDate())
            );
        }

        return ActivityWindow.of(mappedActivities);
    }

    ActivityEntity mapToActivityEntity(Activity activity) {
        return new ActivityEntity(
                activity.getReference(),
                activity.getOwnerAccountNumber().getValue(),
                activity.getSourceAccountNumber().getValue(),
                activity.getTargetAccountNumber().getValue(),
                activity.getAmount().getValue(),
                activity.getTransactionDate()
        );
    }

}
