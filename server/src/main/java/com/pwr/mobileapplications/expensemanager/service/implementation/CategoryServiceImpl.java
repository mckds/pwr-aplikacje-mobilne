package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.mysql.cj.util.StringUtils;
import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;
import com.pwr.mobileapplications.expensemanager.exception.BudgetNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.CategoryAlreadyExistsException;
import com.pwr.mobileapplications.expensemanager.exception.CategoryNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.InvalidCategoryNameException;
import com.pwr.mobileapplications.expensemanager.model.Category;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	public CategoryDto findById(Long id) {
		return CategoryDto.from(getCategoryById(id));
	}

	@Override
	public List<CategoryDto> findAll(Long id) {
		List<CategoryDto> categories = new ArrayList<>();
		categoryRepository.findAllByBudget_BudgetId(id).forEach(category -> categories.add(CategoryDto.from(category)));
		return categories;
	}

	@Override
	public CategoryDto deleteByNameAndBudgetId(String name, Long budgetId) {
		Category category = categoryRepository.findByNameAndBudget_BudgetId(name, budgetId)
				.orElseThrow(CategoryNotFoundException::new);
		categoryRepository.delete(category);
		return CategoryDto.from(category);
	}

	@Override
	public CategoryDto editCategory(EditCategoryDto dto) {
		checkCategoryName(dto.getNewName());
		if(categoryRepository.findByNameAndBudget_BudgetId(dto.getName(), dto.getBudgetId()).isPresent()){
			throw new CategoryAlreadyExistsException();
		}
		Category category = categoryRepository.findByNameAndBudget_BudgetId(dto.getName(), dto.getBudgetId())
				.orElseThrow(CategoryNotFoundException::new);
		category.setName(dto.getNewName());
		categoryRepository.save(category);
		return CategoryDto.from(category);
	}

	@Override
	public CategoryDto addNewCategory(NewCategoryDto dto) {
		checkCategoryName(dto.getName());
		if(categoryRepository.findByNameAndBudget_BudgetId(dto.getName(), dto.getBudgetId()).isPresent()){
			throw new CategoryAlreadyExistsException();
		}
		Category category = new Category();
		category.setBudget(budgetRepository.findById(dto.getBudgetId()).orElseThrow(BudgetNotFoundException::new));
		category.setName(dto.getName());
		return CategoryDto.from(categoryRepository.save(category));
	}

	@Override
	public List<CategoryDto> findByBudgetId(Long id) {
		List<CategoryDto> categories = new ArrayList<>();
		categoryRepository.findByBudget_BudgetId(id).forEach(category -> categories.add(CategoryDto.from(category)));
		return categories;
	}

	private Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
	}

	private void checkCategoryName(String name) {
		if(StringUtils.isEmptyOrWhitespaceOnly(name)){
			throw new InvalidCategoryNameException();
		}
	}
}
