package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.ExpenseDto;
import com.pwr.mobileapplications.expensemanager.dto.NewExpenseDto;
import com.pwr.mobileapplications.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	private final ExpenseService expenseService;

	@Autowired
	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@PostMapping()
	public ResponseEntity<ExpenseDto> addExpense(@RequestBody NewExpenseDto dto){
		return new ResponseEntity<>(expenseService.addNewExpense(dto), HttpStatus.CREATED);
	}

	@GetMapping("budgets/{budgetId}")
	public ResponseEntity<List<ExpenseDto>> getExpenses(@PathVariable Long budgetId){
		return ResponseEntity.ok(expenseService.findAll(budgetId));

	}

	@DeleteMapping("accounts/{expenseId}")
	public ResponseEntity<ExpenseDto> deleteExpense(@PathVariable Long expenseId){
		return new ResponseEntity<>(expenseService.delete(expenseId), HttpStatus.NO_CONTENT);
	}

}
