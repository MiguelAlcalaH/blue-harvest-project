package com.blueharvest.bank.blueharvest.application.impl;

import com.blueharvest.bank.blueharvest.application.CustomerService;
import com.blueharvest.bank.blueharvest.domain.Customer;
import com.blueharvest.bank.blueharvest.infrastructure.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerDetails(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
    
    @Override
    public List<Customer> getCustomersDetails() {
        return customerRepository.findAll();
    }
}
