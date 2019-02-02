package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.exception.AccountAlreadyExists;
import com.pwr.mobileapplications.expensemanager.exception.AccountNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.BudgetNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.service.BudgetAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetAccountServiceImpl implements BudgetAccountService {

	private final AccountRepository accountRepository;
	private final BudgetRepository budgetRepository;

	@Autowired
	public BudgetAccountServiceImpl(AccountRepository accountRepository, BudgetRepository budgetRepository) {
		this.accountRepository = accountRepository;
		this.budgetRepository = budgetRepository;
	}

	@Override
	public void addAccountToBudget(AccountDto dto, Long budgetId) {
		Account account = ((dto.getAccountId() == null)?
				accountRepository.findByUsername(dto.getUsername()) : accountRepository.findById(dto.getAccountId()))
						.orElseThrow(() -> new AccountNotFoundException("Account Not found"));
		Budget budget = budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new);
		if(budget.getAccounts().contains(account)){
			throw new AccountAlreadyExists("Account in budget" + budgetId + " already exists");
		}
		budget.getAccounts().add(account);
		budgetRepository.save(budget);
	}

	@Override
	public List<AccountDto> getAllByBudgetId(Long budgetId) {
		List<AccountDto> accounts = new ArrayList<>();
		Budget budget = budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new);
		budget.getAccounts().forEach(account -> accounts.add(AccountDto.from(account)));
		return accounts;
	}

	@Override
	public void deleteAccountFromBudget(Long accountId, Long budgetId) {
		Budget budget = budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new);
		budget.getAccounts().removeIf(account -> account.getAccountId().equals(accountId));
		budgetRepository.save(budget);
	}
}
