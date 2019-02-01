package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {

	CategoryDto findById(Long id);

	List<CategoryDto> findAll(Long id);

	CategoryDto deleteByNameAndBudgetId(String name, Long id);

	CategoryDto editCategory(EditCategoryDto dto);

	CategoryDto addNewCategory(NewCategoryDto dto);

	List<CategoryDto> findByBudgetId(Long id);
}
