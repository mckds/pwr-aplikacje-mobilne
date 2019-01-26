package com.pwr.mobileapplications.expensemanager.repository;

import com.pwr.mobileapplications.expensemanager.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

