package io.ashimjk.hexagonalarchitecture.account.adapter.in.web;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class AccountApi {

    public static RequestSpecification postRequest(int port) {
        return RestAssured.given()
                          .baseUri("http://localhost")
                          .port(port)
                          .when()
                          .contentType("application/json");
    }

}
