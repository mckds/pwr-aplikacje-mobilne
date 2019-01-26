package com.pwr.mobileapplications.expensemanager.repository;

import com.pwr.mobileapplications.expensemanager.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
