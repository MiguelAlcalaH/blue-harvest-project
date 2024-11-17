package com.blueharvest.bank.blueharvest.application.impl;

import com.blueharvest.bank.blueharvest.application.AccountService;
import com.blueharvest.bank.blueharvest.domain.Account;
import com.blueharvest.bank.blueharvest.domain.Transaction;
import com.blueharvest.bank.blueharvest.infrastructure.AccountRepository;
import com.blueharvest.bank.blueharvest.infrastructure.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account openAccount(Long customerId, BigDecimal initialCredit) {
        // Create a new account with zero balance initially
        Account newAccount = Account.builder()
                .balance(BigDecimal.ZERO)
                .transactions(new ArrayList<>())
                .build();
        newAccount = accountRepository.save(newAccount);

        // If initialCredit > 0, create a new transaction and update account balance
        if (initialCredit.compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = Transaction.builder()
                    .amount(initialCredit)
                    .timestamp(LocalDateTime.now())
                    .account(newAccount)
                    .build();
            transactionRepository.save(transaction);

            newAccount.setBalance(initialCredit);
            accountRepository.save(newAccount);
        }

        return newAccount;
    }

    @Override
    public Account getAccountDetails(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
    }
}