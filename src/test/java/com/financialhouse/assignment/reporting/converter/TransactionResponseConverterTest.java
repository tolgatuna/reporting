package com.financialhouse.assignment.reporting.converter;

import com.financialhouse.assignment.reporting.bootstrap.TransactionBuilder;
import com.financialhouse.assignment.reporting.model.Transaction;
import com.financialhouse.assignment.swagger.model.TransactionResponseData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionResponseConverterTest {
    private Transaction transaction;
    private TransactionResponseConverter transactionResponseConverter;

    @Before
    public void setUp() throws Exception {
        CustomerInfoConverter customerResponseConverter = new CustomerInfoConverter();
        transactionResponseConverter = new TransactionResponseConverter(customerResponseConverter);
        transaction = TransactionBuilder.createTransaction1();
    }

    @Test
    public void shouldReturnTransactionResponseObjectForGivenTransaction() {
        TransactionResponseData responseData = transactionResponseConverter.convert(transaction);
        assertEquals(responseData.getTransaction().getMerchant().getTransactionId(), transaction.getMerchant().getTransactionId());
        assertEquals(responseData.getMerchant().getName(), transaction.getMerchant().getName());
        assertEquals(responseData.getFx().getMerchant().getOriginalAmount(), transaction.getFx().getMerchant().getOriginalAmount());
        assertEquals(responseData.getCustomerInfo().getEmail(), transaction.getCustomerInfo().getEmail());
    }
}