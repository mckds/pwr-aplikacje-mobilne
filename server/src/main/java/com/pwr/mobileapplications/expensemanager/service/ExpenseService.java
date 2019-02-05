package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.ExpenseDto;
import com.pwr.mobileapplications.expensemanager.dto.NewExpenseDto;

import java.util.List;

public interface ExpenseService {

	List<ExpenseDto> findAll(Long budgetId);
	ExpenseDto addNewExpense(NewExpenseDto dto, String userName);
}
