package com.pwr.mobileapplications.expensemanager.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class NewCategoryDto {
	@NotNull
	private Long budgetId;
	@NotNull
	private String name;
}
