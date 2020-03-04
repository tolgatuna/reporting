package com.financialhouse.assignment.reporting.converter;

import com.financialhouse.assignment.reporting.model.CustomerInfo;
import com.financialhouse.assignment.reporting.model.*;
import com.financialhouse.assignment.swagger.model.*;
import org.springframework.stereotype.Component;

@Component
public class TransactionListResponseConverter {

    TransactionResponseConverter transactionResponseConverter;

    public TransactionListResponseConverter(TransactionResponseConverter transactionResponseConverter) {
        this.transactionResponseConverter = transactionResponseConverter;
    }

    public TransactionListData convert(Transaction transaction) {
        TransactionListData listData = new TransactionListData();
        listData.setAcquirer(convertTransactionListDataAcquirer(transaction.getAcquirer()));
        listData.setCustomerInfo(convertTransactionListCustomerInfo(transaction.getCustomerInfo()));
        listData.setFx(transactionResponseConverter.convertTransactionListFxData(transaction.getFx()));
        listData.setIpn(transactionResponseConverter.convertTransactionListIpn(transaction.getIpn()));
        listData.setRefundable(transaction.getRefundable());
        listData.setTransaction(convertTransactionListDataTransaction(transaction.getTransaction()));
        listData.setMerchant(convertTransactionListDataMerchant(transaction.getMerchant()));
        return listData;
    }

    private TransactionListDataCustomerInfo convertTransactionListCustomerInfo(CustomerInfo customerInfo) {
        TransactionListDataCustomerInfo transactionListDataCustomerInfo = new TransactionListDataCustomerInfo();
        if (customerInfo != null) {
            transactionListDataCustomerInfo.setEmail(customerInfo.getEmail());
            transactionListDataCustomerInfo.setNumber(customerInfo.getNumber());
            transactionListDataCustomerInfo.setBillingFirstName(customerInfo.getBillingFirstName());
            transactionListDataCustomerInfo.setBillingLastName(customerInfo.getBillingLastName());
        }

        return transactionListDataCustomerInfo;
    }

    private TransactionListDataAcquirer convertTransactionListDataAcquirer(Acquirer acquirer) {
        TransactionListDataAcquirer transactionListDataAcquirer = new TransactionListDataAcquirer();
        if (acquirer != null) {
            transactionListDataAcquirer.setId(acquirer.getId());
            transactionListDataAcquirer.setCode(acquirer.getCode());
            transactionListDataAcquirer.setName(acquirer.getName());
            transactionListDataAcquirer.setType(acquirer.getType());
        }
        return transactionListDataAcquirer;
    }

    private TransactionListDataTransaction convertTransactionListDataTransaction(TransactionInfo transactionInfo) {
        TransactionListDataTransaction listDataTransaction = new TransactionListDataTransaction();
        TransactionListDataTransactionMerchant merchant = new TransactionListDataTransactionMerchant();
        if (transactionInfo != null) {
            Merchant transactionMerchant = transactionInfo.getMerchant();
            if (transactionMerchant != null) {

                merchant.setCreatedAt(transactionMerchant.getCreated_at().toString());
                merchant.setTransactionId(transactionMerchant.getTransactionId());
                merchant.setMessage(transactionMerchant.getMessage());
                merchant.setOperation(transactionMerchant.getOperation());
                merchant.setReferenceNo(transactionMerchant.getReferenceNo());
                merchant.setStatus(transactionMerchant.getStatus());
            }
        }
        listDataTransaction.setMerchant(merchant);
        return listDataTransaction;
    }

    private TransactionListDataMerchant convertTransactionListDataMerchant(Merchant merchant) {
        TransactionListDataMerchant transactionListDataMerchant = new TransactionListDataMerchant();
        if (merchant != null) {
            transactionListDataMerchant.setId(merchant.getId());
            transactionListDataMerchant.setName(merchant.getName());
        }
        return transactionListDataMerchant;
    }
}
