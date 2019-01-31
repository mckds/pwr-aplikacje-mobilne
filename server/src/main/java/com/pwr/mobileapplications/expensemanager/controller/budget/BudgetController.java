package com.pwr.mobileapplications.expensemanager.controller.budget;

import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class BudgetController {

	private final BudgetService budgetService;

	@Autowired
	public BudgetController(BudgetService budgetService) {
		this.budgetService = budgetService;
	}

	@GetMapping("budgets/{budgetId}")
	public ResponseEntity<BudgetDto> getBudgets(@PathVariable Long budgetId) {
		return ResponseEntity.ok(budgetService.findById(budgetId));
	}

	@PostMapping("/budgets")
	public ResponseEntity<BudgetDto> createNewBudget(@RequestBody BudgetDto budget) {
		return ResponseEntity.ok(budgetService.addNewBudget(budget));
	}

	@GetMapping("/budgets")
	public ResponseEntity<List<BudgetDto>> getAll() {
		return ResponseEntity.ok(budgetService.findAll());
	}
	@PutMapping("/budgets")
	public ResponseEntity<BudgetDto> addAccountToBudget(@RequestBody BudgetDto budget) {
		return ResponseEntity.ok(budgetService.addNewBudget(budget));
	}


}
