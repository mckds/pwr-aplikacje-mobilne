package com.pwr.mobileapplications.expensemanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class NewExpenseDto {
	private Long categoryId;
	private Long accountId;
	private Long budgetId;
	private Double amount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate date;
}
