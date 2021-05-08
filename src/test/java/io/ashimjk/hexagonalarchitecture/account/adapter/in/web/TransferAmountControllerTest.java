package io.ashimjk.hexagonalarchitecture.account.adapter.in.web;

import io.ashimjk.hexagonalarchitecture.account.adapter.in.web.CreateAccountController.CreateAccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.ashimjk.hexagonalarchitecture.account.adapter.in.web.TransferAmountController.TransferAmountRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransferAmountControllerTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Transfer Amount")
    void transferAmount() {

        String sourceAccountNumber = "SRC-123456789";
        String targetAccountNumber = "TGT-123456789";

        createAccount(sourceAccountNumber, 30.00);
        createAccount(targetAccountNumber, 20.00);

        TransferAmountRequest request = new TransferAmountRequest(sourceAccountNumber, targetAccountNumber, 10.00);

        AccountApi.postRequest(port)
                  .body(request)
                  .post("/accounts/transfer")
                  .then()
                  .statusCode(201);
    }

    private void createAccount(final String accountNumber, final double amount) {
        AccountApi.postRequest(port)
                  .body(new CreateAccountRequest(accountNumber, amount))
                  .post("/accounts")
                  .then()
                  .statusCode(201);

    }

}