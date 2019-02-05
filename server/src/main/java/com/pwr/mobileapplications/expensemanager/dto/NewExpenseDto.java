package com.pwr.mobileapplications.expensemanager.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class NewExpenseDto {
	@NotNull
	private Long categoryId;
	@NotNull
	private Long budgetId;
	@NotNull
	private Double amount;
	@NotNull
	private LocalDate date;
}
