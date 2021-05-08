package io.ashimjk.hexagonalarchitecture.account.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
class ActivityEntity {

    @Id
    private String reference;
    private String ownerAccountNumber;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

}
