package com.pwr.mobileapplications.expensemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BudgetNotFoundException extends RuntimeException {
    public BudgetNotFoundException() {
        super("Budget not found");
    }

    public BudgetNotFoundException(String message) {
        super(message);
    }
}
