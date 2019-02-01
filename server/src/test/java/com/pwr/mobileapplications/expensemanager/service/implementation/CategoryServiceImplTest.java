package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;
import com.pwr.mobileapplications.expensemanager.exception.CategoryAlreadyExistsException;
import com.pwr.mobileapplications.expensemanager.exception.CategoryNotFoundException;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.pwr.mobileapplications.expensemanager.service.implementation.CategoryMother.getOptionalCategory;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	@Mock
	private BudgetRepository budgetRepository;

	private CategoryService categoryService;

	@Before
	public void setup(){
		categoryService = new CategoryServiceImpl(categoryRepository, budgetRepository);
		when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
		when(categoryRepository.findByNameAndBudget_BudgetId("food", 1L)).thenReturn(getOptionalCategory());
		when(categoryRepository.findByNameAndBudget_BudgetId("drinks", 1L)).thenReturn(Optional.empty());
	}

	@Test(expected = CategoryNotFoundException.class)
	public void ifIdDoesNotExistThrowCategoryNotFoundException(){
		categoryService.findById(1L);
	}

	@Test(expected = CategoryNotFoundException.class)
	public void ifDeletedObjectDoesNotExistThrowCategoryNotFoundException(){
		categoryService.deleteByNameAndBudgetId("drinks", 1L);
	}

	@Test
	public void deleteMethodShouldReturnDeletedCategory(){
		CategoryDto dto = categoryService.deleteByNameAndBudgetId("food", 1L);
		Assert.assertThat(dto.getName(), equalTo("Food"));
	}

	@Test
	public void editedCategoryShouldReturnDtoWithNewName(){
		EditCategoryDto editCategoryDto = new EditCategoryDto();
		editCategoryDto.setName("food");
		editCategoryDto.setNewName("restaurant");
		editCategoryDto.setBudgetId(1L);

		CategoryDto dto = categoryService.editCategory(editCategoryDto);

		Assert.assertThat(dto.getName(), equalTo("restaurant"));
	}

	@Test(expected = CategoryAlreadyExistsException.class)
	public void ifCategoryExistAddNewCategoryShouldThrowException() {
		NewCategoryDto dto = new NewCategoryDto();
		dto.setName("food");
		dto.setBudgetId(1L);
		categoryService.addNewCategory(dto);
	}

}