package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.exception.InvalidCategoryNameException;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CategoryServiceNameTest {

	@Mock
	private CategoryRepository categoryRepository;

	private CategoryService categoryService;

	@Before
	public void setup(){
		categoryService = new CategoryServiceImpl(categoryRepository);
	}

	@Test(expected = InvalidCategoryNameException.class)
	public void cratedCategoryNameCannotBeNull(){
		CategoryDto dto = new CategoryDto();
		categoryService.addNewCategory(dto);
	}
	@Test(expected = InvalidCategoryNameException.class)
	public void cratedCategoryNameCannotBeEmpty(){
		CategoryDto dto = new CategoryDto();
		dto.setName("");
		categoryService.addNewCategory(dto);
	}

	@Test(expected = InvalidCategoryNameException.class)
	public void editedCategoryNameCannotBeNull(){
		EditCategoryDto dto = new EditCategoryDto();
		categoryService.editCategory(dto);
	}
	@Test(expected = InvalidCategoryNameException.class)
	public void editedCategoryNameCannotBeEmpty(){
		EditCategoryDto dto = new EditCategoryDto();
		dto.setName("");
		categoryService.editCategory(dto);
	}
}
