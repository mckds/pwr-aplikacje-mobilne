package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;
import com.pwr.mobileapplications.expensemanager.exception.BudgetNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.CategoryAlreadyExistsException;
import com.pwr.mobileapplications.expensemanager.exception.InvalidCategoryNameException;
import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.model.Category;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final BudgetRepository budgetRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository, BudgetRepository budgetRepository) {
		this.categoryRepository = categoryRepository;
		this.budgetRepository = budgetRepository;
	}

	@Override
	public CategoryDto addNewCategory(NewCategoryDto dto) {
		checkCategoryName(dto.getName());
		Budget budget = budgetRepository.findById(dto.getBudgetId()).orElseThrow(BudgetNotFoundException::new);
		List<Category> categories = budget.getCategories();
		for(Category c : categories){
			if(c.getName().equals(dto.getName())) {
				throw new CategoryAlreadyExistsException();
			}
		}
		Category category = new Category();
		category.setBudget(budget);
		category.setName(dto.getName());
		return CategoryDto.from(categoryRepository.save(category));
	}

	private void checkCategoryName(String name) {
		if(name.isBlank()){
			throw new InvalidCategoryNameException();
		}
	}
}
