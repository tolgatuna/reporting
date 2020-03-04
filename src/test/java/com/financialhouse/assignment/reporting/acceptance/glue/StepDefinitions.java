package com.financialhouse.assignment.reporting.acceptance.glue;

import com.financialhouse.assignment.reporting.ReportingApplication;
import com.financialhouse.assignment.reporting.bootstrap.TransactionBuilder;
import com.financialhouse.assignment.reporting.model.Transaction;
import com.financialhouse.assignment.reporting.repository.TransactionRepository;
import com.financialhouse.assignment.swagger.model.LoginRequestData;
import io.cucumber.java8.En;
import io.cucumber.junit.Cucumber;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Cucumber.class)
@SpringBootTest(classes = ReportingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles(value = "test")
public class StepDefinitions implements En {

    @Autowired
    private TransactionRepository transactionRepository;

    private LoginRequestData loginRequestData;

    private Response response;

    public StepDefinitions() {
        Given("^I have a user credentials$", () -> {
            loginRequestData = new LoginRequestData();
            loginRequestData.setEmail("demo@financialhouse.io");
            loginRequestData.setPassword("cjaiU8CV");
        });
        When("^I request to login with the credentials", () -> {
            response = (Response) given()
                    .headers(getHeaders())
                    .body(loginRequestData)
                    .post("/api/v3/merchant/user/login")
                    .getBody();
        });
        Then("^I should see the authorization token$", () -> {
            response.then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("status", equalTo("APPROVED"))
                    .body("token", notNullValue());
        });

        Given("^I have transactions in repository$", () -> {
            transactionRepository.deleteAll();
            Transaction transaction1 = TransactionBuilder.createTransaction1();
            Transaction transaction2 = TransactionBuilder.createTransaction2();
            Transaction transaction3 = TransactionBuilder.createTransaction3();
            transactionRepository.saveOrUpdate(transaction1.getMerchant().getTransactionId(), transaction1);
            transactionRepository.saveOrUpdate(transaction2.getMerchant().getTransactionId(), transaction2);
            transactionRepository.saveOrUpdate(transaction3.getMerchant().getTransactionId(), transaction3);
        });
        When("^I request for report$", () -> {
            response = (Response) given()
                    .headers(getHeaders())
                    .body("{ \"fromDate\": \"2015-07-01\", \"toDate\": \"2020-02-01\" }")
                    .post("/api/v3/transactions/report")
                    .getBody();
        });
        Then("^I should see transactions report$", () -> {
            response.then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("status", equalTo("APPROVED"))
                    .body("response", notNullValue())
                    .body("response[0].total", equalTo(150))
                    .body("response[0].currency", equalTo("TRY"))
                    .body("response[0].count", equalTo(1));
        });

        When("^I request for transaction list$", () -> {
            response = (Response) given()
                    .headers(getHeaders())
                    .body("{ \"fromDate\": \"2015-07-01\", \"toDate\": \"2020-02-01\" }")
                    .post("/api/v3/transaction/list")
                    .getBody();
        });
        Then("^I should see transaction list$", () -> {
            response.then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("data", notNullValue())
                    .body("data[0].fx.merchant.originalAmount", equalTo(150))
                    .body("data[0].customerInfo.email", equalTo("bedirhan@bumin.io"))
                    .body("data[0].merchant.name", equalTo("Seckin Merchant"))
                    .body("data[0].transaction.merchant.referenceNo", equalTo("trn-test-seck-1"));

        });

        When("^I request for a transaction with transaction ID$", () -> {
            response = (Response) given()
                    .headers(getHeaders())
                    .body("{ \"transactionId\": \"1010904-1539176472-1293\" }")
                    .post("/api/v3/transaction")
                    .getBody();
        });
        Then("^I should see the transaction$", () -> {
            response.then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("fx.merchant.originalAmount", equalTo(16000))
                    .body("customerInfo.email", equalTo("seckin@bumin.io"))
                    .body("merchant.name", equalTo("Seckin Merchant"))
                    .body("transaction.merchant.referenceNo", equalTo("trn-test-seck-1"));
        });

        When("I request to get customer data from repository with transaction ID", () -> {
            response = (Response) given()
                    .headers(getHeaders())
                    .body("{ \"transactionId\": \"1010904-1539176472-1293\" }")
                    .post("/api/v3/client")
                    .getBody();
        });

        Then("I should see the customer data", () -> {
            response.then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("customerInfo.number", equalTo("411111XXXXXX1111"))
                    .body("customerInfo.email", equalTo("seckin@bumin.io"))
                    .body("customerInfo.billingFirstName", equalTo("SECKIN"))
                    .body("customerInfo.billingLastName", equalTo("SEN"));
        });
    }

    private Headers getHeaders() {
        List<Header> headerList = new ArrayList<>();
        headerList.add(new Header("Content-Type", "application/json"));
        return new Headers(headerList);
    }
}
