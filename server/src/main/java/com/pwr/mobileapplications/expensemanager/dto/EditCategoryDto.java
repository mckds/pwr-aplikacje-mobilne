package com.pwr.mobileapplications.expensemanager.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class EditCategoryDto {

	@NotNull
	private Long budgetId;
	@NotNull
	private String name;

	@NotNull
	private String newName;
}
