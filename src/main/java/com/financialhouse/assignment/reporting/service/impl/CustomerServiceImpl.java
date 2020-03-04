package com.financialhouse.assignment.reporting.service.impl;

import com.financialhouse.assignment.reporting.converter.CustomerInfoConverter;
import com.financialhouse.assignment.reporting.repository.TransactionRepository;
import com.financialhouse.assignment.reporting.service.CustomerService;
import com.financialhouse.assignment.swagger.model.CustomerInfo;
import com.financialhouse.assignment.swagger.model.CustomerResponseData;
import com.financialhouse.assignment.swagger.model.TransactionRequestData;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private TransactionRepository transactionRepository;
    private CustomerInfoConverter customerInfoConverter;

    public CustomerServiceImpl(TransactionRepository transactionRepository, CustomerInfoConverter customerInfoConverter) {
        this.transactionRepository = transactionRepository;
        this.customerInfoConverter = customerInfoConverter;
    }

    @Override
    public CustomerResponseData getCustomerInfo(TransactionRequestData transactionRequestData) {
        CustomerInfo customerInfo = customerInfoConverter.convert(transactionRepository.getById(transactionRequestData.getTransactionId()));
        CustomerResponseData customerResponseData = new CustomerResponseData();
        customerResponseData.setCustomerInfo(customerInfo);
        return customerResponseData;
    }
}
