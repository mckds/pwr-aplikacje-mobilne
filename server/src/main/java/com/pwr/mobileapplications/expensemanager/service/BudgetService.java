package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;

import java.util.List;

public interface BudgetService {
	BudgetDto findById(Long id);

	BudgetDto addNewBudget(BudgetDto budgetDto);

	List<BudgetDto> findAll();
}
