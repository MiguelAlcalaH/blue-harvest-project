package com.blueharvest.bank.blueharvest.infrastructure;

import com.blueharvest.bank.blueharvest.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}