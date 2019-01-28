package com.pwr.mobileapplications.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BudgetGetDto {
    private long budgetId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double expenditureLimit;


}
