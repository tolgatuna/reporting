package com.financialhouse.assignment.reporting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financialhouse.assignment.reporting.ReportingApplication;
import com.financialhouse.assignment.swagger.model.LoginRequestData;
import com.financialhouse.assignment.swagger.model.LoginResponseData;
import com.financialhouse.assignment.swagger.model.TransactionRequestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("security")
@SpringBootTest(classes = ReportingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${jwt.timeout}")
    private long tokenTimeout;

    private static final String FINANCIAL_HOUSE_EMAIL = "demo@financialhouse.io";
    public static final String FINANCIAL_HOUSE_PASSWORD = "cjaiU8CV";

    @Test
    public void shouldAllowRequestWhenAuthorizationHeaderIsValid() throws Exception {
        LoginRequestData requestData = new LoginRequestData();
        requestData.setEmail(FINANCIAL_HOUSE_EMAIL);
        requestData.setPassword(FINANCIAL_HOUSE_PASSWORD);
        HttpHeaders httpHeadersForLogin = new HttpHeaders();
        httpHeadersForLogin.add("Content-Type", "application/json");
        MvcResult result = mockMvc.perform(post("/api/v3/merchant/user/login")
                .headers(httpHeadersForLogin)
                .content(objectMapper.writeValueAsBytes(requestData)))
                .andExpect(status().isOk())
                .andReturn();

        LoginResponseData loginResponseData = objectMapper.readValue(result.getResponse().getContentAsString(), LoginResponseData.class);
        TransactionRequestData transactionRequestData = new TransactionRequestData();
        transactionRequestData.setTransactionId("TEST");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", loginResponseData.getToken());
        httpHeaders.add("Content-Type", "application/json");
        mockMvc.perform(
                post("/api/v3/client")
                        .headers(httpHeaders)
                        .content(objectMapper.writeValueAsBytes(transactionRequestData))
        ).andExpect(status().isOk());
    }

    @Test
    public void shouldNotLoginWhenCredentialsWrong() throws Exception {
        LoginRequestData requestData = new LoginRequestData();
        requestData.setEmail("WRONG_MAIL");
        requestData.setPassword("...");
        HttpHeaders httpHeadersForLogin = new HttpHeaders();
        httpHeadersForLogin.add("Content-Type", "application/json");
        mockMvc.perform(post("/api/v3/merchant/user/login")
                .headers(httpHeadersForLogin)
                .content(objectMapper.writeValueAsBytes(requestData)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotAllowRequestWhenNoAuthorizationHeader() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        mockMvc.perform(post("/api/v3/client").headers(httpHeaders))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotAllowRequestWhenInvalidTokenInAuthorization() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "INVALID TOKEN");
        httpHeaders.add("Content-Type", "application/json");
        mockMvc.perform(post("/api/v3/client").headers(httpHeaders))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotAllowRequestWhenExpiredTokenInAuthorization() throws Exception {
        LoginRequestData requestData = new LoginRequestData();
        requestData.setEmail(FINANCIAL_HOUSE_EMAIL);
        requestData.setPassword(FINANCIAL_HOUSE_PASSWORD);
        HttpHeaders httpHeadersForLogin = new HttpHeaders();
        httpHeadersForLogin.add("Content-Type", "application/json");
        MvcResult result = mockMvc.perform(post("/api/v3/merchant/user/login")
                .headers(httpHeadersForLogin)
                .content(objectMapper.writeValueAsBytes(requestData)))
                .andExpect(status().isOk())
                .andReturn();

        LoginResponseData loginResponseData = objectMapper.readValue(result.getResponse().getContentAsString(), LoginResponseData.class);
        Thread.sleep(tokenTimeout * 1000 + 1);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", loginResponseData.getToken());
        mockMvc.perform(get("/api/v3/client").headers(httpHeaders))
                .andExpect(status().isUnauthorized());
    }
}