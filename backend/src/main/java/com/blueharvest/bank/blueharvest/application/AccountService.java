package com.blueharvest.bank.blueharvest.application;

import com.blueharvest.bank.blueharvest.domain.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account openAccount(Long customerId, BigDecimal initialCredit);
    Account getAccountDetails(Long accountId);
}
