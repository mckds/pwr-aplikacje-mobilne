package com.pwr.mobileapplications.expensemanager.exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException() {
    }

    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
