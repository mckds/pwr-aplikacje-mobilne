package com.pwr.mobileapplications.expensemanager.exception;

public class BudgetAlreadyExists extends RuntimeException {
	public BudgetAlreadyExists() {
		super("Budget already exists");
	}
}
