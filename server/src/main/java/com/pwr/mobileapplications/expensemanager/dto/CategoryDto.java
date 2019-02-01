package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Category;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@RequiredArgsConstructor
public class CategoryDto {
	private static final ModelMapper modelMapper = new ModelMapper();

	private String name;

	public static CategoryDto from(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

	public Category toCategory() {
		return modelMapper.map(this, Category.class);
	}
}
