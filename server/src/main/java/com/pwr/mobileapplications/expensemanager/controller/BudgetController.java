package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		return new ResponseEntity<>(budgetService.addNewBudget(budget), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BudgetDto> deleteBudget(@PathVariable Long id){
		return new ResponseEntity<>(budgetService.deleteBudgetById(id), HttpStatus.NO_CONTENT);
	}

	@GetMapping()
	public ResponseEntity<List<BudgetDto>> getAll() {
		return ResponseEntity.ok(budgetService.findAll());
	}

}
