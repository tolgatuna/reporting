package com.financialhouse.assignment.reporting.converter;

import com.financialhouse.assignment.reporting.model.Transaction;
import com.financialhouse.assignment.swagger.model.CustomerInfo;
import org.springframework.stereotype.Component;

@Component
public class CustomerInfoConverter {
    public CustomerInfo convert(Transaction transaction) {
        CustomerInfo customerInfo = new CustomerInfo();
        if (transaction != null && transaction.getCustomerInfo() != null) {
            com.financialhouse.assignment.reporting.model.CustomerInfo transactionCustomerInfo = transaction.getCustomerInfo();

            customerInfo.setBillingAddress1(transactionCustomerInfo.getBillingAddress1());
            customerInfo.setBillingAddress2(transactionCustomerInfo.getBillingAddress2());
            customerInfo.setBillingCity(transactionCustomerInfo.getBillingCity());
            customerInfo.setBillingCompany(transactionCustomerInfo.getBillingCompany());
            customerInfo.setBillingCountry(transactionCustomerInfo.getBillingCountry());
            customerInfo.setBillingFax(transactionCustomerInfo.getBillingFax());
            customerInfo.setBillingFirstName(transactionCustomerInfo.getBillingFirstName());
            customerInfo.setBillingLastName(transactionCustomerInfo.getBillingLastName());
            customerInfo.setBillingPhone(transactionCustomerInfo.getBillingPhone());
            customerInfo.setBillingPostCode(transactionCustomerInfo.getBillingPostcode());
            customerInfo.setBillingState(transactionCustomerInfo.getBillingState());
            customerInfo.setBillingTitle(transactionCustomerInfo.getBillingTitle());

            customerInfo.setShippingAddress1(transactionCustomerInfo.getShippingAddress1());
            customerInfo.setShippingAddress2(transactionCustomerInfo.getShippingAddress2());
            customerInfo.setShippingCity(transactionCustomerInfo.getShippingCity());
            customerInfo.setShippingCompany(transactionCustomerInfo.getShippingCompany());
            customerInfo.setShippingCountry(transactionCustomerInfo.getShippingCountry());
            customerInfo.setShippingFax(transactionCustomerInfo.getShippingFax());
            customerInfo.setShippingFirstName(transactionCustomerInfo.getShippingFirstName());
            customerInfo.setShippingLastName(transactionCustomerInfo.getShippingLastName());
            customerInfo.setShippingPhone(transactionCustomerInfo.getShippingPhone());
            customerInfo.setShippingPostCode(transactionCustomerInfo.getShippingPostcode());
            customerInfo.setShippingState(transactionCustomerInfo.getShippingState());
            customerInfo.setShippingTitle(transactionCustomerInfo.getShippingTitle());

            customerInfo.setStartMonth(transactionCustomerInfo.getStartMonth());
            customerInfo.setStartYear(transactionCustomerInfo.getStartYear());
            customerInfo.setBirthday(transactionCustomerInfo.getBirthday());
            customerInfo.setCreatedAt(transactionCustomerInfo.getCreated_at());
            customerInfo.setDeletedAt(transactionCustomerInfo.getDeleted_at());
            customerInfo.setUpdatedAt(transactionCustomerInfo.getUpdated_at());
            customerInfo.setEmail(transactionCustomerInfo.getEmail());
            customerInfo.setExpiryMonth(transactionCustomerInfo.getExpiryMonth());
            customerInfo.setExpiryYear(transactionCustomerInfo.getExpiryYear());
            customerInfo.setGender(transactionCustomerInfo.getGender());
            customerInfo.setId(transactionCustomerInfo.getId());
            customerInfo.setIssueNumber(transactionCustomerInfo.getIssueNumber());
            customerInfo.setNumber(transactionCustomerInfo.getNumber());
        }
        return customerInfo;
    }
}
