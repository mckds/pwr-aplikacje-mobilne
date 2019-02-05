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
	public AccountDto addAccountToBudget(AccountToBudgetDto dto, String accountName) {
		Account loggedUser = accountRepository.findByUsername(accountName).orElseThrow(AccountNotFoundException::new);
		Account account = accountRepository.findById(dto.getAccountId()).orElseThrow(AccountNotFoundException::new);
		Budget budget = budgetRepository.findById(dto.getBudgetId()).orElseThrow(BudgetNotFoundException::new);
		if (!budget.getAccounts().contains(loggedUser)) {
			throw new InvalidValueException("You cannot do that");
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
		Budget budget = budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new);
		checkAccess(userName, budget.getAccounts());
		List<AccountExpensesDto> accounts = new ArrayList<>();
		budget.getAccounts().forEach(a -> accounts.add(AccountExpensesDto.from(a)));
		return accounts;
	}

	@Override
	public List<AccountDto> getUnassignedAccounts(Long budgetId, String userName) {
		Budget budget = budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new);
		checkAccess(userName, budget.getAccounts());
		List<Account> accounts = accountRepository.findAll();
		budget.getAccounts().forEach(accounts::remove);
		List<AccountDto> accountDtos = new ArrayList<>();
		accounts.forEach(a -> accountDtos.add(AccountDto.from(a)));
		return accountDtos;
	}

	private void checkAccess(String userName, List<Account> accounts) {
		Account account = accountRepository.findByUsername(userName).orElseThrow(AccountNotFoundException::new);
		if (!accounts.contains(account)) {
			throw new BudgetNotFoundException();
		}
	}

	@Override
	public void deleteAccountFromBudget(Long accountId, Long budgetId) {
		Budget budget = budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new);
		budget.getAccounts().removeIf(account -> account.getAccountId().equals(accountId));
		budgetRepository.save(budget);
	}

	@Override
	public List<BudgetDto> findAllByUserName(String userName) {
		Account account = accountRepository.findByUsername(userName).orElseThrow(AccountNotFoundException::new);
		List<BudgetDto> budgets = new ArrayList<>();
		account.getBudgets().forEach(b -> budgets.add(BudgetDto.from(b)));
		return budgets;
	}

	@Override
	public BudgetDto createNewBudget(String userName, BudgetDto dto) {
		Account account = accountRepository.findByUsername(userName).orElseThrow(AccountNotFoundException::new);
		if (budgetRepository.findByName(dto.getName()).isPresent()) {
			throw new BudgetAlreadyExists();
		}
		Budget budget = dto.toBudget();
		budget.getAccounts().add(account);
		return BudgetDto.from(budgetRepository.save(budget));
	}

	@Override
	public BudgetDto findBudgetById(Long id, String userName) {
		Budget budget = budgetRepository.findById(id).orElseThrow(BudgetNotFoundException::new);
		Account account = accountRepository.findByUsername(userName).orElseThrow(AccountNotFoundException::new);
		if (budget.getAccounts().contains(account)) {
			return BudgetDto.from(budget);
		}
		throw new BudgetNotFoundException();
	}
}
