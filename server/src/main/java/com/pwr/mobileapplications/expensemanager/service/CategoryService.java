package com.pwr.mobileapplications.expensemanager.service;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;

import java.util.List;

public interface CategoryService {

	CategoryDto findById(Long id);

	List<CategoryDto> findAll();

	CategoryDto deleteByName(String name);

	CategoryDto editCategory(EditCategoryDto dto);

	CategoryDto addNewCategory(CategoryDto dto);
}
