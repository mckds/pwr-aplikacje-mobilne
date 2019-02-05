package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountExpensesDto;
import com.pwr.mobileapplications.expensemanager.dto.AccountToBudgetDto;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets/{budgetId}")
public class BudgetDetailsController {

	private final BudgetService budgetService;

	@Autowired
	public BudgetDetailsController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@PostMapping("/accounts")
	public ResponseEntity<AccountDto> addAccountToBudget(@RequestBody AccountToBudgetDto accountDto) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<>(budgetService.addAccountToBudget(accountDto, userName), HttpStatus.CREATED);
	}

	@GetMapping("/accounts")
	public ResponseEntity<List<AccountExpensesDto>> getAccounts(@PathVariable Long budgetId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(budgetService.getAccountsInBudget(budgetId, userName));
	}

	@GetMapping("/unassigned")
	public ResponseEntity<List<AccountDto>> getUnassignedAccounts(@PathVariable Long budgetId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(budgetService.getUnassignedAccounts(budgetId, userName));
	}

}
