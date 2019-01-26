package com.pwr.mobileapplications.expensemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int budgetId;

    private LocalDate startDate;
    private LocalDate endDate;
    private double limit;

    @OneToMany(mappedBy = "budget")
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "budget")
    private List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "budget")
    private List<Category> categories = new ArrayList<>();
}
