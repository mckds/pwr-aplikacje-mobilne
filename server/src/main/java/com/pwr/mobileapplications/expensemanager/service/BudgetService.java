package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;

public interface BudgetService {
	BudgetDto findById(Long id);

	BudgetDto addNewBudget(BudgetDto budgetDto);
}
