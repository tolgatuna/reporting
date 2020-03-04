package com.financialhouse.assignment.reporting.service.impl;

import com.financialhouse.assignment.reporting.converter.TransactionListResponseConverter;
import com.financialhouse.assignment.reporting.converter.TransactionResponseConverter;
import com.financialhouse.assignment.reporting.model.Transaction;
import com.financialhouse.assignment.reporting.repository.TransactionRepository;
import com.financialhouse.assignment.reporting.service.TransactionService;
import com.financialhouse.assignment.swagger.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionListResponseConverter transactionListResponseConverter;
    private TransactionResponseConverter transactionResponseConverter;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionListResponseConverter transactionListResponseConverter, TransactionResponseConverter transactionResponseConverter) {
        this.transactionRepository = transactionRepository;
        this.transactionListResponseConverter = transactionListResponseConverter;
        this.transactionResponseConverter = transactionResponseConverter;
    }

    @Override
    public TransactionReportResponseData getTransactionReport(TransactionReportRequestData transactionReportRequestData) {
        Map<String, List<Transaction>> groupedTransactions = transactionRepository.listAll().stream()
                .filter(transaction -> filterFromDate(transactionReportRequestData.getFromDate(), transaction.getCreated_at()))
                .filter(transaction -> filterToDate(transactionReportRequestData.getToDate(), transaction.getCreated_at()))
                .filter(transaction -> filterByField(transactionReportRequestData.getMerchant(), transaction.getTransaction().getMerchant().getMerchantId()))
                .filter(transaction -> filterByField(transactionReportRequestData.getAcquirer(), transaction.getAcquirer().getId()))
                .collect(Collectors.groupingBy(transaction -> transaction.getMerchant().getCurrency()));


        TransactionReportResponseData transactionReportResponseData = new TransactionReportResponseData();
        transactionReportResponseData.setStatus("APPROVED");
        groupedTransactions.forEach((s, transactions) -> {
            TransactionReportResponseDataResponse responseItem = new TransactionReportResponseDataResponse();
            responseItem.setCurrency(s);
            responseItem.setCount(transactions.size());
            responseItem.setTotal(transactions.stream().map(t -> t.getMerchant().getAmount()).reduce(0, Integer::sum));
            transactionReportResponseData.addResponseItem(responseItem);
        });
        return transactionReportResponseData;
    }

    @Override
    public TransactionListResponseData getTransactionList(Integer page, TransactionListRequestData transactionListRequestData) {

        page = transactionListRequestData.getPage() != null ? transactionListRequestData.getPage() : page;

        List<TransactionListData> transactionListData = transactionRepository.listAll().stream()
                .filter(transaction -> filterFromDate(transactionListRequestData.getFromDate(), transaction.getCreated_at()))
                .filter(transaction -> filterToDate(transactionListRequestData.getToDate(), transaction.getCreated_at()))
                .filter(transaction -> filterByField(transactionListRequestData.getStatus(), transaction.getMerchant().getStatus()))
                .filter(transaction -> filterByField(transactionListRequestData.getOperation(), transaction.getMerchant().getOperation()))
                .filter(transaction -> filterByField(transactionListRequestData.getMerchantId(), transaction.getTransaction().getMerchant().getMerchantId()))
                .filter(transaction -> filterByField(transactionListRequestData.getAcquirerId(), transaction.getAcquirer().getId()))
                .filter(transaction -> filterByField(transactionListRequestData.getPaymentMethod(), transaction.getMerchant().getPaymentType()))
                .filter(transaction -> filterByField(transactionListRequestData.getErrorCode(), transaction.getMerchant().getCode()))
                .filter(transaction -> filterByCustomField(transactionListRequestData.getFilterValue(), transactionListRequestData.getFilterField(), transaction))
                .skip((page - 1) * 50)
                .limit(50)
                .map(transactionListResponseConverter::convert)
                .collect(Collectors.toList());

        TransactionListResponseData transactionListResponseData = new TransactionListResponseData();
        transactionListResponseData.setCurrentPage(page);
        transactionListResponseData.setPerPage(50);
        transactionListResponseData.setFrom((page - 1) * 50 + 1);
        transactionListResponseData.setTo(page * 50);
        transactionListResponseData.setPrevPageUrl(page > 1 ? ("/api/v3/transaction/list?page=" + (page - 1)) : null);
        transactionListResponseData.setNextPageUrl(transactionListData.size() == 50 ? ("/api/v3/transaction/list?page=" + (page + 1)) : null);
        transactionListResponseData.setData(transactionListData);
        return transactionListResponseData;
    }


    @Override
    public TransactionResponseData getTransaction(TransactionRequestData transactionRequestData) {
        Transaction transactionById = transactionRepository.getById(transactionRequestData.getTransactionId());
        return transactionResponseConverter.convert(transactionById);
    }

    private boolean filterFromDate(LocalDate fromDate, LocalDate transactionCreatedDate) {
        return null == fromDate || !transactionCreatedDate.isBefore(fromDate);
    }

    private boolean filterToDate(LocalDate toDate, LocalDate transactionCreatedDate) {
        return null == toDate || !transactionCreatedDate.isAfter(toDate);
    }

    private boolean filterByField(String filterValue, String transactionValue) {
        return null == filterValue || filterValue.equals(transactionValue);
    }

    private boolean filterByField(Integer filterValue, Integer transactionValue) {
        return null == filterValue || filterValue.equals(transactionValue);
    }

    private boolean filterByCustomField(String filterValue, String filterField, Transaction transaction) {
        if (filterField != null && filterValue != null) {
            if (filterField.equals("Transaction UUID")) {
                return transaction.getMerchant().getTransactionId().equals(filterValue);
            } else if (filterField.equals("Customer Email")) {
                return transaction.getCustomerInfo().getEmail().equals(filterValue);
            } else if (filterField.equals("Reference No")) {
                return transaction.getMerchant().getReferenceNo().equals(filterValue);
            } else if (filterField.equals("Custom Data")) {
                return transaction.getMerchant().getCustomData().equals(filterValue);
            } else if (filterField.equals("Card PAN")) {
                // Not known exactly which field.
                return transaction.getMerchant().getType().equals(filterValue);
            } else {
                return false;
            }
        }
        return true;
    }

}
