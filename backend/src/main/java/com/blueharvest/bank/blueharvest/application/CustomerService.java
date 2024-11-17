package com.blueharvest.bank.blueharvest.application;

import com.blueharvest.bank.blueharvest.domain.Customer;

public interface CustomerService {
    Customer getCustomerDetails(Long customerId);
}
