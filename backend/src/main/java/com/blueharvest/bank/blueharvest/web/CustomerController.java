package com.blueharvest.bank.blueharvest.web;


import com.blueharvest.bank.blueharvest.application.CustomerService;
import com.blueharvest.bank.blueharvest.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomersDetails() {
        return ResponseEntity.ok(customerService.getCustomersDetails());
    }
    
    @GetMapping("/{customerId}")
    public Customer getCustomerDetails(@PathVariable Long customerId) {
        return customerService.getCustomerDetails(customerId);
    }
}