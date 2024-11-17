package com.blueharvest.bank.blueharvest.web;

import com.blueharvest.bank.blueharvest.application.AccountService;
import com.blueharvest.bank.blueharvest.domain.Account;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public Account openAccount(@RequestParam Long customerId, @RequestParam BigDecimal initialCredit) {
        return accountService.openAccount(customerId, initialCredit);
    }

    @GetMapping("/{accountId}")
    public Account getAccountDetails(@PathVariable Long accountId) {
        return accountService.getAccountDetails(accountId);
    }
}
