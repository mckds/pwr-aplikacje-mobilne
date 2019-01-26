package com.pwr.mobileapplications.expensemanager.model;

import java.time.LocalDate;

public class Expense {
    private int expenseId;

    private double amount;
    private LocalDate date;

    private Account account;
    private Category category;
}
