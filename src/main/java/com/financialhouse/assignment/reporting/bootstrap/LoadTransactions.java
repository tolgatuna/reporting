package com.financialhouse.assignment.reporting.bootstrap;

import com.financialhouse.assignment.reporting.repository.TransactionRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import static com.financialhouse.assignment.reporting.bootstrap.TransactionBuilder.*;

@Component
public class LoadTransactions implements ApplicationListener<ContextRefreshedEvent> {

    private TransactionRepository transactionRepository;

    public LoadTransactions(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        transactionRepository.saveOrUpdate("1010904-1539176472-1293", createTransaction1());
        transactionRepository.saveOrUpdate("1009720-1537364596-1293", createTransaction2());
        transactionRepository.saveOrUpdate("1011026-1539357059-1293", createTransaction3());
    }


}
