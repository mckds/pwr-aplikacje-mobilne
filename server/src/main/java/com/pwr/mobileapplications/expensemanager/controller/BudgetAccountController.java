package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountExpensesDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountToBudgetDto;
import com.pwr.mobileapplications.expensemanager.service.BudgetAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets/{budgetId}")
public class BudgetAccountController {

	private final BudgetAccountService budgetAccountService;

	@Autowired
	public BudgetAccountController(BudgetAccountService budgetAccountService) {
		this.budgetAccountService = budgetAccountService;
	}

	@PostMapping("/accounts")
	public ResponseEntity<AccountDto> addAccountToBudget(@RequestBody AccountToBudgetDto accountDto) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<>(budgetAccountService.addAccountToBudget(accountDto, userName), HttpStatus.CREATED);
	}

	@DeleteMapping("/accounts/{accountId}")
	public ResponseEntity deleteAccountFromBudget(@PathVariable Long budgetId, @PathVariable Long accountId) {
		budgetAccountService.deleteAccountFromBudget(accountId, budgetId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<AccountExpensesDto>> getAccounts(@PathVariable Long budgetId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(budgetAccountService.getAccountsInBudget(budgetId, userName));
	}

	@GetMapping("/unassigned")
	public ResponseEntity<List<AccountDto>> getUnassignedAccounts(@PathVariable Long budgetId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(budgetAccountService.getUnassignedAccounts(budgetId, userName));
	}

}
