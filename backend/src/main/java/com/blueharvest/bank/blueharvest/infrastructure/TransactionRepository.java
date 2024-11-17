package com.blueharvest.bank.blueharvest.infrastructure;

import com.blueharvest.bank.blueharvest.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}