package com.financialhouse.assignment.reporting.converter;

import com.financialhouse.assignment.reporting.bootstrap.TransactionBuilder;
import com.financialhouse.assignment.reporting.model.Transaction;
import com.financialhouse.assignment.swagger.model.CustomerInfo;
import org.junit.Assert;
import org.junit.Test;

public class CustomerInfoConverterTest {

    private CustomerInfoConverter customerInfoConverter = new CustomerInfoConverter();

    @Test
    public void shouldConvertAsEmptyCustomerResponseWhenTransactionNull() {
        Assert.assertNotNull(customerInfoConverter.convert(null));
    }

    @Test
    public void shouldConvertAsEmptyCustomerResponseWhenTransactionCustomerInfoNull() {
        Transaction transaction = new Transaction();
        transaction.setCustomerInfo(null);
        CustomerInfo customerInfo = customerInfoConverter.convert(transaction);
        Assert.assertNotNull(customerInfo);
    }

    @Test
    public void shouldConvertSuccessfullyValidTransactionCustomerInfo() {
        Transaction transaction = TransactionBuilder.createTransaction1();
        CustomerInfo customerInfo = customerInfoConverter.convert(transaction);
        Assert.assertEquals("411111XXXXXX1111", customerInfo.getNumber());
        Assert.assertEquals("seckin@bumin.io", customerInfo.getEmail());
        Assert.assertEquals("SECKIN", customerInfo.getBillingFirstName());
        Assert.assertEquals("SEN", customerInfo.getBillingLastName());
    }
}