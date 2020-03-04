package com.financialhouse.assignment.reporting.service;

import com.financialhouse.assignment.swagger.model.*;

public interface TransactionService {
    TransactionReportResponseData getTransactionReport(TransactionReportRequestData transactionReportRequestData);

    TransactionListResponseData getTransactionList(Integer page, TransactionListRequestData transactionListRequestData);

    TransactionResponseData getTransaction(TransactionRequestData transactionRequestData);
}
