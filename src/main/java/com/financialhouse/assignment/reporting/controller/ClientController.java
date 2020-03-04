package com.financialhouse.assignment.reporting.controller;

import com.financialhouse.assignment.reporting.service.CustomerService;
import com.financialhouse.assignment.swagger.model.CustomerResponseData;
import com.financialhouse.assignment.swagger.model.TransactionRequestData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v3")
public class ClientController {

    private CustomerService customerService;

    public ClientController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/client")
    public CustomerResponseData getCustomerInfo(@RequestBody TransactionRequestData transactionRequestData) throws Exception {
        return customerService.getCustomerInfo(transactionRequestData);
    }
}
