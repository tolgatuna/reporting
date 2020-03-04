package com.financialhouse.assignment.reporting.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Transaction {
    private LocalDate updated_at;
    private LocalDate created_at;
    private CustomerInfo customerInfo;
    private Fx fx;
    private TransactionInfo transaction;
    private Merchant merchant;
    private Acquirer acquirer;
    private Boolean refundable;
    private Ipn ipn;
}
