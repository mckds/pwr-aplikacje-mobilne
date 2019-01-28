package com.pwr.mobileapplications.expensemanager.exception;

public class BudgetNotFoundException extends RuntimeException {
    public BudgetNotFoundException() {
    }

    public BudgetNotFoundException(String message) {
        super(message);
    }
}
