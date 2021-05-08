package io.ashimjk.hexagonalarchitecture.account.adapter.in.web;

import io.ashimjk.hexagonalarchitecture.account.adapter.in.web.CreateAccountController.CreateAccountRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static io.ashimjk.hexagonalarchitecture.account.adapter.in.web.CreateAccountController.CreateAccountResponse;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateAccountControllerTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Create Account")
    void createAccount() {
        CreateAccountRequest request = new CreateAccountRequest("AC-123456789", 22.00);

        CreateAccountResponse response = AccountApi.postRequest(port)
                                                   .body(request)
                                                   .post("/accounts")
                                                   .then()
                                                   .statusCode(201)
                                                   .extract()
                                                   .as(CreateAccountResponse.class);

        assertThat(response).isNotNull();
        assertThat(response.getAccountNumber()).isEqualTo("AC-123456789");
        assertThat(response.getBalance()).isEqualByComparingTo(BigDecimal.valueOf(22.00));
    }

}