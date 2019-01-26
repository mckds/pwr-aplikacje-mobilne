package com.pwr.mobileapplications.expensemanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    private int budgetId;

    private LocalDate startDate;
    private LocalDate endDate;
    private double limit;

    private List<Account> accounts = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
}
