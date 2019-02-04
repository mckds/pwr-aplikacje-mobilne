package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.AccountDto;
import com.pwr.mobileapplications.expensemanager.service.BudgetAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets/{budgetId}/accounts")
public class BudgetAccountController {

	private final BudgetAccountService budgetAccountService;

	@Autowired
	public BudgetAccountController(BudgetAccountService budgetAccountService) {
		this.budgetAccountService = budgetAccountService;
	}

	@PostMapping()
	public ResponseEntity addAccountToBudget(@PathVariable Long budgetId, @RequestBody AccountDto accountDto){
		budgetAccountService.addAccountToBudget(accountDto, budgetId);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@DeleteMapping("{accountId}")
	public ResponseEntity deleteAccountFromBudget(@PathVariable Long budgetId, @PathVariable Long accountId){
		budgetAccountService.deleteAccountFromBudget(accountId, budgetId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable Long budgetId){
		return ResponseEntity.ok(budgetAccountService.getAllByBudgetId(budgetId));
	}

}
