package io.ashimjk.strategicaccount.account.adapter.out.persistence;

import io.ashimjk.strategicaccount.account.application.port.out.CreateAccountStatePort;
import io.ashimjk.strategicaccount.account.application.port.out.LoadAccountPort;
import io.ashimjk.strategicaccount.account.application.port.out.UpdateAccountStatePort;
import io.ashimjk.strategicaccount.account.common.PersistenceAdapter;
import io.ashimjk.strategicaccount.account.domain.Account;
import io.ashimjk.strategicaccount.account.domain.AccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
class AccountRepositoryAdapter implements LoadAccountPort, UpdateAccountStatePort, CreateAccountStatePort {

    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(final AccountNumber accountNumber) {

        AccountEntity accountEntity = this.accountRepository.findById(accountNumber.getValue())
                                                            .orElseThrow(EntityNotFoundException::new);

        List<ActivityEntity> activityEntities = this.activityRepository.findByOwnerAccountNumber(accountEntity.getAccountNumber());

        BigDecimal depositBalance = this.activityRepository.getDepositBalance(accountEntity.getAccountNumber())
                                                           .orElse(BigDecimal.ZERO);
        BigDecimal withdrawalBalance = this.activityRepository.getWithdrawalBalance(accountEntity.getAccountNumber())
                                                              .orElse(BigDecimal.ZERO);

        return this.accountMapper.mapToDomainEntity(accountEntity,
                                                    activityEntities,
                                                    withdrawalBalance,
                                                    depositBalance);
    }

    @Override
    public void updateAccount(final Account account) {
        List<ActivityEntity> activityEntities = account.getActivityWindow().getActivities()
                                                       .stream()
                                                       .map(accountMapper::mapToActivityEntity)
                                                       .collect(Collectors.toList());

        activityRepository.saveAll(activityEntities);
    }

    @Override
    @Transactional
    public Account create(final Account account) {

        AccountEntity accountEntity = new AccountEntity(account.getAccountNumber().getValue());

        this.updateAccount(account);
        this.accountRepository.save(accountEntity);

        return account;
    }

}
