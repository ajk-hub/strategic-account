package io.ashimjk.hexagonalarchitecture.account.adapter.in.web;

import io.ashimjk.hexagonalarchitecture.account.application.port.in.CreateAccountCommand;
import io.ashimjk.hexagonalarchitecture.account.application.port.in.CreateAccountUseCase;
import io.ashimjk.hexagonalarchitecture.account.common.WebAdapter;
import io.ashimjk.hexagonalarchitecture.account.domain.Account;
import io.ashimjk.hexagonalarchitecture.account.domain.AccountNumber;
import io.ashimjk.hexagonalarchitecture.account.domain.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;

@WebAdapter
@RequiredArgsConstructor
class CreateAccountController {

    private final CreateAccountUseCase createAccountUseCase;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    CreateAccountResponse create(@RequestBody CreateAccountRequest request) {

        final CreateAccountCommand createAccountCommand = CreateAccountCommand.of(
                AccountNumber.of(request.accountNumber),
                Amount.of(request.amount)
        );

        Account account = createAccountUseCase.create(createAccountCommand);
        return new CreateAccountResponse(
                account.getAccountNumber().getValue(),
                account.getBaselineAmount().getValue()
        );

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class CreateAccountRequest {

        private String accountNumber;
        private Double amount;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class CreateAccountResponse {

        private String accountNumber;
        private BigDecimal balance;

    }

}
