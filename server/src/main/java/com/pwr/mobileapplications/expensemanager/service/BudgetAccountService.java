package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountExpensesDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountToBudgetDto;
import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;

import java.util.List;

public interface BudgetAccountService {

	AccountDto addAccountToBudget(AccountToBudgetDto dto, String accountName);
	List<AccountExpensesDto> getAccountsInBudget(Long budgetId, String userName);
	void deleteAccountFromBudget(Long accountId, Long budgetId);

	List<BudgetDto> findAllByUserName(String userName);

	BudgetDto createNewBudget(String userName, BudgetDto dto);

	BudgetDto findBudgetById(Long id, String userName);

	List<AccountDto> getUnassignedAccounts(Long budgetId, String userName);
}
