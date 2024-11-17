package com.blueharvest.bank.blueharvest.web;

import com.blueharvest.bank.blueharvest.application.AccountService;
import com.blueharvest.bank.blueharvest.domain.Account;
import com.blueharvest.bank.blueharvest.web.dto.OpenAccountRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/test")
    public ResponseEntity<String> testEndpoint(@RequestBody String rawRequest) {
        log.info("Received raw request: {}", rawRequest);
        return ResponseEntity.ok("Success");
    }
    @PostMapping("")
    public ResponseEntity<Account> openAccount(@Valid @RequestBody OpenAccountRequest request) {
        log.info("Opening account {}", request);
        return ResponseEntity.ok(accountService.openAccount(request.getCustomerId(), request.getInitialCredit()));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long accountId) {
        log.info("Getting account details for account {}", accountId);
        return ResponseEntity.ok(accountService.getAccountDetails(accountId));
    }
}
