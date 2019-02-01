package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
class BudgetController {

	private final BudgetService budgetService;

	@Autowired
	public BudgetController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<BudgetDto> getBudgets(@PathVariable Long id) {
		return ResponseEntity.ok(budgetService.findById(id));
	}

	@PostMapping()
	public ResponseEntity<BudgetDto> createNewBudget(@RequestBody BudgetDto budget) {
		return ResponseEntity.ok(budgetService.addNewBudget(budget));
	}

	@GetMapping()
	public ResponseEntity<List<BudgetDto>> getAll() {
		return ResponseEntity.ok(budgetService.findAll());
	}

	@PutMapping()
	public ResponseEntity<BudgetDto> addAccountToBudget(@RequestBody BudgetDto budget) {
		return ResponseEntity.ok(budgetService.addNewBudget(budget));
	}


}
