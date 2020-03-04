package com.financialhouse.assignment.reporting.converter;

import com.financialhouse.assignment.reporting.model.*;
import com.financialhouse.assignment.swagger.model.*;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponseConverter {

    private CustomerInfoConverter customerInfoConverter;

    public TransactionResponseConverter(CustomerInfoConverter customerInfoConverter) {
        this.customerInfoConverter = customerInfoConverter;
    }

    public TransactionResponseData convert(Transaction transaction) {
        TransactionResponseData transactionResponseData = new TransactionResponseData();
        transactionResponseData.setFx(convertTransactionListFxData(transaction.getFx()));
        transactionResponseData.setIpn(convertTransactionListIpn(transaction.getIpn()));
        transactionResponseData.setMerchant(convertTransactionMerchantResponse(transaction.getMerchant()));
        transactionResponseData.setCustomerInfo(customerInfoConverter.convert(transaction));
        transactionResponseData.setTransaction(convertTransactionResponseDataTransaction(transaction.getTransaction()));
        return transactionResponseData;
    }

    public TransactionListDataFx convertTransactionListFxData(Fx fx) {
        TransactionListDataFx transactionListDataFx = new TransactionListDataFx();
        TransactionListDataFxMerchant merchant = new TransactionListDataFxMerchant();
        if (fx != null && fx.getMerchant() != null) {
            merchant.setOriginalAmount(fx.getMerchant().getOriginalAmount());
            merchant.setOriginalCurrency(fx.getMerchant().getOriginalCurrency());
        }
        transactionListDataFx.setMerchant(merchant);
        return transactionListDataFx;
    }

    public TransactionListDataIpn convertTransactionListIpn(Ipn ipn) {
        TransactionListDataIpn transactionListDataIpn = new TransactionListDataIpn();
        if (ipn != null) {
            transactionListDataIpn.setReceived(ipn.getSent());
        }
        return transactionListDataIpn;
    }

    public TransactionResponseDataMerchant convertTransactionMerchantResponse(Merchant merchant) {
        TransactionResponseDataMerchant transactionResponseDataMerchant = new TransactionResponseDataMerchant();
        if (merchant != null) {
            transactionResponseDataMerchant.setName(merchant.getName());
        }
        return transactionResponseDataMerchant;
    }

    public TransactionResponseDataTransaction convertTransactionResponseDataTransaction(TransactionInfo transaction) {
        TransactionResponseDataTransaction transactionResponseDataTransaction = new TransactionResponseDataTransaction();
        TransactionResponseDataTransactionMerchant merchant = new TransactionResponseDataTransactionMerchant();
        Merchant transactionMerchant = transaction.getMerchant();
        if (transactionMerchant != null) {
            merchant.setCode(transactionMerchant.getCode());
            merchant.setReferenceNo(transactionMerchant.getReferenceNo());
            merchant.setAcquirerTransactionId(transactionMerchant.getAcquirerTransactionId());
            merchant.setChannel(transactionMerchant.getChannel());
            merchant.setCreatedAt(transactionMerchant.getCreated_at().toString());
            merchant.setCustomData(transactionMerchant.getCustomData());
            merchant.setMessage(transactionMerchant.getMessage());
            merchant.setTransactionId(transactionMerchant.getTransactionId());
            merchant.setUpdatedAt(transactionMerchant.getUpdated_at() != null ? transactionMerchant.getUpdated_at().toString() : null);
            merchant.setMerchantId(transactionMerchant.getMerchantId());
            merchant.setChainId(transactionMerchant.getChainId());
            merchant.setAgentInfoId(transactionMerchant.getAgentInfoId());
            merchant.setId(transactionMerchant.getId());
            merchant.setOperation(transactionMerchant.getOperation());
            merchant.setFxTransactionId(transactionMerchant.getFxTransactionId());
            merchant.setStatus(transactionMerchant.getStatus());
            if (transactionMerchant.getAgent() != null) {
                TransactionResponseDataTransactionMerchantAgent agent = new TransactionResponseDataTransactionMerchantAgent();
                agent.setCustomerUserAgent(transactionMerchant.getAgent().getCustomerUserAgent());
                agent.setCustomerUserIp(transactionMerchant.getAgent().getCustomerIp());
                agent.setId(transactionMerchant.getAgent().getId());
                agent.setMerchantIp(transactionMerchant.getAgent().getMerchantIp());
                agent.setCreatedAt(transactionMerchant.getAgent().getCreated_at());
                agent.setUpdatedAt(transactionMerchant.getAgent().getUpdated_at());
                agent.setDeletedAt(transactionMerchant.getAgent().getDeleted_at());
                merchant.setAgent(agent);
            }
        }
        transactionResponseDataTransaction.setMerchant(merchant);
        return transactionResponseDataTransaction;
    }

}
