package com.pwr.mobileapplications.expensemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expenseId;
    private double amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    public Expense() {
    }

    public Expense(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public Expense(double amount, LocalDate date, Account account, Category category, Budget budget) {
        this.amount = amount;
        this.date = date;
        this.account = account;
        this.category = category;
        this.budget = budget;
    }
}

