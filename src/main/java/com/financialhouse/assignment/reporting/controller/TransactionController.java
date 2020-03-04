package com.financialhouse.assignment.reporting.controller;

import com.financialhouse.assignment.reporting.service.TransactionService;
import com.financialhouse.assignment.swagger.model.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v3")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transactions/report")
    public TransactionReportResponseData getTransactionsReport(@RequestBody TransactionReportRequestData transactionReportRequestData) throws Exception {
        return transactionService.getTransactionReport(transactionReportRequestData);
    }

    @PostMapping(value = "/transaction/list")
    public TransactionListResponseData getTransactionList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestBody TransactionListRequestData transactionListRequestData) throws Exception {
        return transactionService.getTransactionList(page, transactionListRequestData);
    }

    @PostMapping(value = "/transaction")
    public TransactionResponseData getTransaction(@RequestBody TransactionRequestData transactionRequestData) throws Exception {
        return transactionService.getTransaction(transactionRequestData);
    }
}
