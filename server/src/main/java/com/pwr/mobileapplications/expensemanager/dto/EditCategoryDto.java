package com.pwr.mobileapplications.expensemanager.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EditCategoryDto {
	private String name;
	private String newName;
}
