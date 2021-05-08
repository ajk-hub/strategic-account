package io.ashimjk.hexagonalarchitecture.account.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
interface ActivityRepository extends JpaRepository<ActivityEntity, String> {

    List<ActivityEntity> findByOwnerAccountNumber(String ownerAccountNumber);

    @Transactional(readOnly = true)
    @Query("select sum(a.amount) from ActivityEntity a where a.targetAccountNumber = :targetAccountNumber ")
    Optional<BigDecimal> getDepositBalance(@Param("targetAccountNumber") String targetAccountNumber);

    @Transactional(readOnly = true)
    @Query("select sum(a.amount) from ActivityEntity a where a.sourceAccountNumber = :sourceAccountNumber ")
    Optional<BigDecimal> getWithdrawalBalance(@Param("sourceAccountNumber") String sourceAccountNumber);

}
