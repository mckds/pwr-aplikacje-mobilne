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
    private Long budgetId;

    @Column(length = 50, unique = true)
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double expenditureLimit;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "budget_account",
            joinColumns = @JoinColumn(name = "budgetId"),
            inverseJoinColumns = @JoinColumn(name = "accountId")
    )
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "budget")
    private List<Expense> expenses = new ArrayList<>();

    // coś trzeba by zrobić z tym eager
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "budget")
    private List<Category> categories = new ArrayList<>();

    public Budget() {
    }

    public Budget(LocalDate startDate, LocalDate endDate, double expenditureLimit) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.expenditureLimit = expenditureLimit;
    }
}
