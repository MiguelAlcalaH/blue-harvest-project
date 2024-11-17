package com.blueharvest.bank.blueharvest.application;


import com.blueharvest.bank.blueharvest.application.impl.AccountServiceImpl;
import com.blueharvest.bank.blueharvest.domain.Account;
import com.blueharvest.bank.blueharvest.domain.Transaction;
import com.blueharvest.bank.blueharvest.infrastructure.AccountRepository;
import com.blueharvest.bank.blueharvest.infrastructure.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void openAccount_WithInitialCredit_ShouldCreateTransaction() {
        // Arrange
        Account account = new Account();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Act
        Account result = accountService.openAccount(1L, BigDecimal.valueOf(1000));

        // Assert
        assertEquals(BigDecimal.valueOf(1000), result.getBalance());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void openAccount_WithZeroInitialCredit_ShouldNotCreateTransaction() {
        // Arrange
        Account account = new Account();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Act
        Account result = accountService.openAccount(1L, BigDecimal.ZERO);

        // Assert
        assertEquals(BigDecimal.ZERO, result.getBalance());
        verify(transactionRepository, times(0)).save(any(Transaction.class));
    }
}