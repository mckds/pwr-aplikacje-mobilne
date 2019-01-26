package com.pwr.mobileapplications.expensemanager.repository;

import com.pwr.mobileapplications.expensemanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
