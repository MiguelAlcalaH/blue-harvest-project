package com.blueharvest.bank.blueharvest.infrastructure;

import com.blueharvest.bank.blueharvest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}