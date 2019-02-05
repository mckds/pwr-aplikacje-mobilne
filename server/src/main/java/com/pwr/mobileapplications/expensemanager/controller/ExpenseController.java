package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.ExpenseDto;
import com.pwr.mobileapplications.expensemanager.dto.NewExpenseDto;
import com.pwr.mobileapplications.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
class ExpenseController {

	private final ExpenseService expenseService;

	@Autowired
	public ExpenseController(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@PostMapping()
	public ResponseEntity<ExpenseDto> addExpense(@RequestBody @Valid NewExpenseDto dto){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<>(expenseService.addNewExpense(dto, userName), HttpStatus.CREATED);
	}

	@GetMapping("budgets/{budgetId}")
	public ResponseEntity<List<ExpenseDto>> getExpenses(@PathVariable Long budgetId){
		return ResponseEntity.ok(expenseService.findAll(budgetId));

	}

}
