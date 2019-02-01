package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;
import com.pwr.mobileapplications.expensemanager.exception.CategoryNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.InvalidCategoryNameException;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceNameTest {

	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private BudgetRepository budgetRepository;

	private CategoryService categoryService;

	@Before
	public void setup(){
		categoryService = new CategoryServiceImpl(categoryRepository, budgetRepository);
		when(categoryRepository.findByNameAndBudget_BudgetId("food",1L)).thenReturn(Optional.empty());
	}

	@Test(expected = InvalidCategoryNameException.class)
	public void cratedCategoryNameCannotBeNull(){
		NewCategoryDto dto = new NewCategoryDto();
		categoryService.addNewCategory(dto);
	}
	@Test(expected = InvalidCategoryNameException.class)
	public void cratedCategoryNameCannotBeEmpty(){
		NewCategoryDto dto = new NewCategoryDto();
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
	@Test(expected = CategoryNotFoundException.class)
	public void addCategoryWithWrongBudgetId(){
		EditCategoryDto dto = new EditCategoryDto();
		dto.setName("food");
		dto.setNewName("drinks");
		dto.setBudgetId(1L);
		categoryService.editCategory(dto);
	}
}
