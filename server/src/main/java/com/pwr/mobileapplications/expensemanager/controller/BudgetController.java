package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;
import com.pwr.mobileapplications.expensemanager.service.BudgetAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
class BudgetController {

	private final BudgetAccountService budgetService;

	@Autowired
	public BudgetController(BudgetAccountService budgetService) {
		this.budgetService = budgetService;
	}


	@GetMapping()
	public ResponseEntity<List<BudgetDto>> getAvailableBudgets() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(budgetService.findAllByUserName(userName));
	}

	@GetMapping("/{id}")
	public ResponseEntity<BudgetDto> getBudget(@PathVariable Long id){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return ResponseEntity.ok(budgetService.findBudgetById(id, userName));
	}

	@PostMapping()
	public ResponseEntity<BudgetDto> createBudget(@RequestBody @Valid BudgetDto dto) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<>(budgetService.createNewBudget(userName, dto), HttpStatus.CREATED);
	}
}
