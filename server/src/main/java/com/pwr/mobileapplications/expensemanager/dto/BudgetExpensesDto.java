package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.model.Expense;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BudgetExpensesDto {
	private Double expenses;

	static BudgetExpensesDto from(Budget budget){
		BudgetExpensesDto dto = new BudgetExpensesDto();
		dto.setExpenses(budget.getExpenses().stream().map(Expense::getAmount).mapToDouble(i -> i).sum());
		return dto;
	}
}
