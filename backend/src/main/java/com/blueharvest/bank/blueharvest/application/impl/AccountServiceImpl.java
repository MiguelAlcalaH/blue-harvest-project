package com.blueharvest.bank.blueharvest.application.impl;

import com.blueharvest.bank.blueharvest.application.AccountService;
import com.blueharvest.bank.blueharvest.domain.Account;
import com.blueharvest.bank.blueharvest.domain.Transaction;
import com.blueharvest.bank.blueharvest.infrastructure.AccountRepository;
import com.blueharvest.bank.blueharvest.infrastructure.CustomerRepository;
import com.blueharvest.bank.blueharvest.infrastructure.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    
    private final CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Account openAccount(Long customerId, BigDecimal initialCredit) {
        log.info("Opening account for customer {} with initial credit {}", customerId, initialCredit);
        Account newAccount = new Account();
                newAccount.setCustomer(customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found")));
                newAccount.setBalance(BigDecimal.ZERO);
                newAccount.setTransactions(new ArrayList<>());
   
        newAccount = accountRepository.save(newAccount);

        // If initialCredit > 0, create a new transaction and update account balance
        if (initialCredit.compareTo(BigDecimal.ZERO) > 0) {
            log.info("Creating initial credit transaction for account {}", newAccount.getAccountId());
            Transaction transaction = new Transaction();
            transaction.setAmount(initialCredit);
            transaction.setTimestamp(LocalDateTime.now());
            transaction.setAccount(newAccount);
            
            transactionRepository.save(transaction);

            newAccount.setBalance(initialCredit);
            accountRepository.save(newAccount);
        }
        log.info("Account opened successfully");
        return newAccount;
    }

    @Override
    public Account getAccountDetails(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
    }
}