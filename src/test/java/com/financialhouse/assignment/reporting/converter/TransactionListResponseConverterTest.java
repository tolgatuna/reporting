package com.financialhouse.assignment.reporting.converter;

import com.financialhouse.assignment.reporting.bootstrap.TransactionBuilder;
import com.financialhouse.assignment.reporting.model.Transaction;
import com.financialhouse.assignment.swagger.model.TransactionListData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TransactionListResponseConverterTest {
    private Transaction transaction;
    private TransactionListResponseConverter transactionListResponseConverter;


    @Before
    public void setUp() throws Exception {
        CustomerInfoConverter customerInfoConverter = new CustomerInfoConverter();
        TransactionResponseConverter transactionResponseConverter = new TransactionResponseConverter(customerInfoConverter);
        transactionListResponseConverter = new TransactionListResponseConverter(transactionResponseConverter);
        transaction = TransactionBuilder.createTransaction1();
    }

    @Test
    public void shouldReturnTransactionListObjectForGivenTransaction() {
        TransactionListData listData = transactionListResponseConverter.convert(transaction);
        assertEquals(listData.getTransaction().getMerchant().getTransactionId(), transaction.getMerchant().getTransactionId());
        assertEquals(listData.getAcquirer().getId(), transaction.getAcquirer().getId());
        assertEquals(listData.getFx().getMerchant().getOriginalAmount(), transaction.getFx().getMerchant().getOriginalAmount());
        assertEquals(listData.getCustomerInfo().getEmail(), transaction.getCustomerInfo().getEmail());
    }

    @Test
    public void shouldNotFailWhenCustomerInfoNotExist() {
        transaction.setCustomerInfo(null);
        TransactionListData listData = transactionListResponseConverter.convert(transaction);
        assertEquals(listData.getTransaction().getMerchant().getTransactionId(), transaction.getMerchant().getTransactionId());
        assertNull(listData.getCustomerInfo().getEmail());
        assertNull(listData.getCustomerInfo().getBillingLastName());
        assertNull(listData.getCustomerInfo().getBillingLastName());
    }

    @Test
    public void shouldNotFailWhenIPNInfoNotExist() {
        transaction.setIpn(null);
        TransactionListData listData = transactionListResponseConverter.convert(transaction);
        assertEquals(listData.getTransaction().getMerchant().getTransactionId(), transaction.getMerchant().getTransactionId());
        assertNull(listData.getIpn().getReceived());
    }
}