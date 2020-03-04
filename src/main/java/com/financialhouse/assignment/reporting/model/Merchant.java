package com.financialhouse.assignment.reporting.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Merchant {
    private String transactionId;
    private String referenceNo;
    private Integer amount;
    private String currency;
    private LocalDate date;
    private String code;
    private String message;
    private String operation;
    private String type;
    private String status;
    private String customData;
    private String chainId;
    private String paymentType;
    private String token;
    private Integer originalAmount;
    private String originalCurrency;
    private Integer convertedAmount;
    private String convertedCurrency;
    private String IPNUrl;
    private String ipnType;
    private Integer merchantId;
    private String channel;
    private Integer agentInfoId;
    private LocalDate updated_at;
    private LocalDate created_at;
    private Integer id;
    private Integer fxTransactionId;
    private Integer acquirerTransactionId;
    private Agent agent;
    private String name;
    private Boolean allowPartialRefund;
    private Boolean allowPartialCapture;
}
