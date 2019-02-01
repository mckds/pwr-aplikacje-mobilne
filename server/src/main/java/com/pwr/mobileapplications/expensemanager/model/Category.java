package com.pwr.mobileapplications.expensemanager.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name", "budget_id"})})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int categoryId;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @OneToMany(mappedBy = "category")
    private List<Expense> expenses = new ArrayList<>();

    public Category() {
    }

    public Category(String name, Budget budget) {
        this.name = name;
        this.budget = budget;
    }
}

