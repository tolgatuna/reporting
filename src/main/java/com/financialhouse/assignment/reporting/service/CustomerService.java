package com.financialhouse.assignment.reporting.service;

import com.financialhouse.assignment.swagger.model.CustomerResponseData;
import com.financialhouse.assignment.swagger.model.TransactionRequestData;

public interface CustomerService {

    CustomerResponseData getCustomerInfo(TransactionRequestData transactionRequestData);
}
