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
    @GeneratedValue
    private long budgetId;

    private LocalDate startDate;
    private LocalDate endDate;

    private double expenditureLimit;

    @OneToMany(mappedBy = "budget")
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "budget")
    private List<Expense> expenses = new ArrayList<>();

    // coś trzeba by zrobić z tym eager
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "budget")
    private List<Category> categories = new ArrayList<>();

    public Budget() {
    }

    public Budget(LocalDate startDate, LocalDate endDate, double expenditureLimit) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.expenditureLimit = expenditureLimit;
    }
}
