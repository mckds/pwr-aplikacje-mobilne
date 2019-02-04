package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;

import java.util.List;

public interface BudgetAccountService {

	void addAccountToBudget(AccountDto dto, Long budgetId);
	List<AccountDto> getAllByBudgetId(Long budgetId);
	void deleteAccountFromBudget(Long accountId, Long budgetId);

	List<BudgetDto> findAllByUserName(String userName);

	void createNewBudget(String userName, BudgetDto dto);
}
