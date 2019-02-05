package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;

public interface CategoryService {
	CategoryDto addNewCategory(NewCategoryDto dto);
}
