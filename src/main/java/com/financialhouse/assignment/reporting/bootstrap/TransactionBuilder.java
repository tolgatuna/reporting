package com.financialhouse.assignment.reporting.bootstrap;

import com.financialhouse.assignment.reporting.model.*;

import java.time.LocalDate;
import java.time.Month;

public class TransactionBuilder {
    public static Transaction createTransaction1() {
        Transaction transaction = new Transaction();

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setNumber("411111XXXXXX1111");
        customerInfo.setEmail("seckin@bumin.io");
        customerInfo.setBillingFirstName("SECKIN");
        customerInfo.setBillingLastName("SEN");

        Merchant merchant = new Merchant();
        merchant.setId(1293);
        merchant.setTransactionId("1010904-1539176472-1293");
        merchant.setReferenceNo("trn-test-seck-1");
        merchant.setName("Seckin Merchant");
        merchant.setAllowPartialRefund(true);
        merchant.setAllowPartialCapture(true);
        merchant.setOriginalAmount(16000);
        merchant.setOriginalCurrency("RUB");
        merchant.setConvertedAmount(16000);
        merchant.setConvertedCurrency("RUB");
        merchant.setStatus("WAITING");
        merchant.setType("AUTH");
        merchant.setOperation("DIRECT");
        merchant.setCode("182");
        merchant.setMessage("Waiting");
        merchant.setCreated_at(LocalDate.of(2018, Month.OCTOBER, 10));
        merchant.setAmount(16000);
        merchant.setCurrency("RUB");
        merchant.setDate(LocalDate.of(2018, Month.OCTOBER, 10));
        merchant.setChainId("5bc0b9831a8b6");
        merchant.setPaymentType("PAYTOCARD");
        merchant.setToken("e408b47fb6107a1b4393b280526860a1265169d64f36e371ca1324af6efb564e");
        merchant.setIPNUrl("https://requestb.in/10bmd651");
        merchant.setIpnType("MERCHANTIPN");

        Fx fx = new Fx();
        fx.setMerchant(merchant);

        Acquirer acquirer = new Acquirer();
        acquirer.setId(115);
        acquirer.setName("RoyalPay PayToCard");
        acquirer.setCode("RP");
        acquirer.setType("PAYTOCARD");

        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setMerchant(merchant);

        transaction.setCreated_at(LocalDate.of(2018, Month.OCTOBER, 10));
        transaction.setUpdated_at(LocalDate.of(2018, Month.OCTOBER, 10));
        transaction.setCustomerInfo(customerInfo);
        transaction.setFx(fx);
        transaction.setAcquirer(acquirer);
        transaction.setTransaction(transactionInfo);
        transaction.setRefundable(false);
        transaction.setMerchant(merchant);
        transaction.setIpn(null);
        return transaction;
    }

    public static Transaction createTransaction2() {
        Transaction transaction = new Transaction();

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setNumber("510139XXXXXX8174");
        customerInfo.setEmail("bedirhan@bumin.io");
        customerInfo.setBillingFirstName("Bedirhan");
        customerInfo.setBillingLastName("Atasoy");

        Merchant merchant = new Merchant();
        merchant.setId(1293);
        merchant.setTransactionId("1009720-1537364596-1293");
        merchant.setReferenceNo("trn-test-seck-1");
        merchant.setName("Seckin Merchant");
        merchant.setAllowPartialRefund(true);
        merchant.setAllowPartialCapture(true);
        merchant.setOriginalAmount(150);
        merchant.setOriginalCurrency("TRY");
        merchant.setConvertedAmount(150);
        merchant.setConvertedCurrency("TRY");
        merchant.setStatus("WAITING");
        merchant.setType("AUTH");
        merchant.setOperation("DIRECT");
        merchant.setCode("182");
        merchant.setMessage("Waiting");
        merchant.setCreated_at(LocalDate.of(2018, Month.SEPTEMBER, 12));
        merchant.setAmount(150);
        merchant.setCurrency("TRY");
        merchant.setDate(LocalDate.of(2018, Month.SEPTEMBER, 12));
        merchant.setChainId("5bc0b9831a8b6");
        merchant.setPaymentType("PAYTOCARD");
        merchant.setToken("e408b47fb6107a1b4393b280526860a1265169d64f36e371ca1324af6efb564e");

        Fx fx = new Fx();
        fx.setMerchant(merchant);

        Acquirer acquirer = new Acquirer();
        acquirer.setId(113);
        acquirer.setName("Speedy PayToCard");
        acquirer.setCode("SP");
        acquirer.setType("PAYTOCARD");

        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setMerchant(merchant);

        transaction.setCreated_at(LocalDate.of(2018, Month.SEPTEMBER, 19));
        transaction.setUpdated_at(LocalDate.of(2018, Month.SEPTEMBER, 19));
        transaction.setCustomerInfo(customerInfo);
        transaction.setFx(fx);
        transaction.setAcquirer(acquirer);
        transaction.setTransaction(transactionInfo);
        transaction.setRefundable(false);
        transaction.setMerchant(merchant);
        transaction.setIpn(null);
        return transaction;
    }

    public static Transaction createTransaction3() {
        Transaction transaction = new Transaction();

        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setNumber("411111XXXXXX1111");
        customerInfo.setEmail("seckin@bumin.io");
        customerInfo.setBillingFirstName("SECKIN");
        customerInfo.setBillingLastName("SEN");

        Merchant merchant = new Merchant();
        merchant.setId(1293);
        merchant.setTransactionId("1011026-1539357059-1293");
        merchant.setReferenceNo("trn-test-seck-1");
        merchant.setName("Seckin Merchant");
        merchant.setAllowPartialRefund(true);
        merchant.setAllowPartialCapture(true);
        merchant.setOriginalAmount(1500);
        merchant.setOriginalCurrency("RUB");
        merchant.setConvertedAmount(1500);
        merchant.setConvertedCurrency("RUB");
        merchant.setStatus("ERROR");
        merchant.setType("AUTH");
        merchant.setOperation("DIRECT");
        merchant.setCode("182");
        merchant.setMessage("Invalid Merchant");
        merchant.setCreated_at(LocalDate.of(2018, Month.OCTOBER, 12));
        merchant.setAmount(1500);
        merchant.setCurrency("RUB");
        merchant.setDate(LocalDate.of(2018, Month.OCTOBER, 12));
        merchant.setChainId("5bc0b9831a8b6");
        merchant.setPaymentType("PAYTOCARD");
        merchant.setToken("e408b47fb6107a1b4393b280526860a1265169d64f36e371ca1324af6efb564e");
        merchant.setIPNUrl("https://requestb.in/10bmd651");
        merchant.setIpnType("MERCHANTIPN");

        Fx fx = new Fx();
        fx.setMerchant(merchant);

        Acquirer acquirer = new Acquirer();
        acquirer.setId(115);
        acquirer.setName("RoyalPay PayToCard");
        acquirer.setCode("RP");
        acquirer.setType("PAYTOCARD");

        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setMerchant(merchant);

        Ipn ipn = new Ipn();
        ipn.setSent(true);
        ipn.setMerchant(merchant);

        transaction.setCreated_at(LocalDate.of(2018, Month.OCTOBER, 12));
        transaction.setUpdated_at(LocalDate.of(2018, Month.OCTOBER, 12));
        transaction.setCustomerInfo(customerInfo);
        transaction.setFx(fx);
        transaction.setAcquirer(acquirer);
        transaction.setTransaction(transactionInfo);
        transaction.setRefundable(false);
        transaction.setMerchant(merchant);
        transaction.setIpn(ipn);
        return transaction;
    }
}
