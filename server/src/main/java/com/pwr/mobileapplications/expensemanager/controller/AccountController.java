package com.pwr.mobileapplications.expensemanager.controller;


import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


	@GetMapping("budgets/{budgetId}")
	public List<BudgetDto> getBudgets(@PathVariable Long accountId, @PathVariable Long budgetId) {
		return Collections.emptyList();
	}
}
