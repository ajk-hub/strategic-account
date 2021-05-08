package io.ashimjk.hexagonalarchitecture.account.adapter.in.web;

import io.ashimjk.hexagonalarchitecture.account.application.port.in.TransferAmountCommand;
import io.ashimjk.hexagonalarchitecture.account.application.port.in.TransferAmountUseCase;
import io.ashimjk.hexagonalarchitecture.account.common.WebAdapter;
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

@WebAdapter
@RequiredArgsConstructor
class TransferAmountController {

    private final TransferAmountUseCase transferAmountUseCase;

    @PostMapping("/accounts/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    void transferAmount(@RequestBody TransferAmountRequest request) {

        TransferAmountCommand transferAmountCommand = TransferAmountCommand.of(
                AccountNumber.of(request.getSourceAccountNumber()),
                AccountNumber.of(request.targetAccountNumber),
                Amount.of(request.amount)
        );

        transferAmountUseCase.transfer(transferAmountCommand);

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class TransferAmountRequest {

        private String sourceAccountNumber;
        private String targetAccountNumber;
        private Double amount;

    }

}
