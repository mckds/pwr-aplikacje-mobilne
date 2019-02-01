package com.pwr.mobileapplications.expensemanager.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class EditCategoryDto {

	private Long budgetId;
	private String name;

	@NotNull
	private String newName;
}
