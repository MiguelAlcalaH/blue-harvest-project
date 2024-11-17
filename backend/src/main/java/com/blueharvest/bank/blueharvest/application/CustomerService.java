package com.blueharvest.bank.blueharvest.application;

import com.blueharvest.bank.blueharvest.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerDetails(Long customerId);

    List<Customer> getCustomersDetails();
}
