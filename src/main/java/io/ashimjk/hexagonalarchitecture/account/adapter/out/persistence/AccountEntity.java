package io.ashimjk.hexagonalarchitecture.account.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
class AccountEntity {

    @Id
    private String accountNumber;

}
