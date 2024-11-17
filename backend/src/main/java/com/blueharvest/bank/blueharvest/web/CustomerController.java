package com.blueharvest.bank.blueharvest.web;


import com.blueharvest.bank.blueharvest.application.CustomerService;
import com.blueharvest.bank.blueharvest.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerDetails(@PathVariable Long customerId) {
        return customerService.getCustomerDetails(customerId);
    }
}