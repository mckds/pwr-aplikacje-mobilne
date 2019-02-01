package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {

	List<ExpenseDto> findAll(Long budgetId);
	ExpenseDto addNewExpense(ExpenseDto dto, Long budgetId);
	ExpenseDto delete(ExpenseDto dto);

}
