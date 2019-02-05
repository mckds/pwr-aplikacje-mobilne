package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountExpensesDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountToBudgetDto;
import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;
import com.pwr.mobileapplications.expensemanager.exception.*;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

	private final AccountRepository accountRepository;
	private final BudgetRepository budgetRepository;

	@Autowired
	public BudgetServiceImpl(AccountRepository accountRepository, BudgetRepository budgetRepository) {
		this.accountRepository = accountRepository;
		this.budgetRepository = budgetRepository;
	}

	@Override
	public AccountDto addAccountToBudget(AccountToBudgetDto dto, String accountName) {
		Account loggedUser = getUserByName(accountName);
		Account account = getAccountById(dto.getAccountId());
		Budget budget = getBudgetById(dto.getBudgetId());
		checkAccess(loggedUser, budget);
		if (!budget.getAccounts().contains(loggedUser)) {
			throw new InvalidValueException("You cannot add yourself");
		}
		if (budget.getAccounts().contains(account)) {
			throw new AccountAlreadyExists("Account in budget" + accountName + " already exists");
		}
		budget.getAccounts().add(account);
		budgetRepository.save(budget);
		return AccountDto.from(account);
	}

	@Override
	public List<AccountExpensesDto> getAccountsInBudget(Long budgetId, String userName) {
		Budget budget = getBudgetById(budgetId);
		checkAccess(userName, budget);
		List<AccountExpensesDto> accounts = new ArrayList<>();
		budget.getAccounts().forEach(a -> accounts.add(AccountExpensesDto.from(a)));
		return accounts;
	}

	@Override
	public List<AccountDto> getUnassignedAccounts(Long budgetId, String userName) {
		Budget budget = getBudgetById(budgetId);
		checkAccess(userName, budget);
		List<Account> accounts = accountRepository.findAll();
		budget.getAccounts().forEach(accounts::remove);
		List<AccountDto> result = new ArrayList<>();
		accounts.forEach(a -> result.add(AccountDto.from(a)));
		return result;
	}

	@Override
	public List<BudgetDto> findAllByUserName(String userName) {
		Account account = getUserByName(userName);
		List<BudgetDto> budgets = new ArrayList<>();
		account.getBudgets().forEach(b -> budgets.add(BudgetDto.from(b)));
		return budgets;
	}

	@Override
	public BudgetDto createNewBudget(String userName, BudgetDto dto) {
		Account account = getUserByName(userName);
		if (budgetRepository.findByName(dto.getName()).isPresent()) {
			throw new BudgetAlreadyExists();
		}
		Budget budget = dto.toBudget();
		budget.getAccounts().add(account);
		return BudgetDto.from(budgetRepository.save(budget));
	}

	@Override
	public BudgetDto findBudgetById(Long id, String userName) {
		Budget budget = getBudgetById(id);
		Account account = getUserByName(userName);
		if (budget.getAccounts().contains(account)) {
			return BudgetDto.from(budget);
		}
		throw new BudgetNotFoundException();
	}

	private Account getUserByName(String name) {
		return accountRepository.findByUsername(name).orElseThrow(AccountNotFoundException::new);
	}
	private Account getAccountById(Long accountId) {
		return accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
	}
	private Budget getBudgetById(Long budgetId) {
		return budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new);
	}
	private void checkAccess(String userName, Budget budget) {
		checkAccess(getUserByName(userName), budget);
	}
	private void checkAccess(Account account, Budget budget) {
		if (!budget.getAccounts().contains(account)) {
			throw new BudgetNotFoundException();
		}
	}

}
