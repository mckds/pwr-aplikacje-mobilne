package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.model.Budget;

import java.time.LocalDate;
import java.util.Optional;

public class BudgetMother {

	public static Budget getBudget(){
		Budget budget = new Budget();
		budget.setBudgetId(1L);
		budget.setStartDate(LocalDate.now().minusDays(7));
		budget.setEndDate(LocalDate.now().plusDays(7));
		budget.setExpenditureLimit(3000d);
		return budget;
	}
	static Optional<Budget> budgetAsOptional(){
		return Optional.of(getBudget());
	}
}
