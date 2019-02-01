package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.mysql.cj.util.StringUtils;
import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.exception.CategoryAlreadyExistsException;
import com.pwr.mobileapplications.expensemanager.exception.CategoryNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.InvalidCategoryNameException;
import com.pwr.mobileapplications.expensemanager.model.Category;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryDto findById(Long id) {
		return CategoryDto.from(getCategoryById(id));
	}

	@Override
	public List<CategoryDto> findAll() {
		List<CategoryDto> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(category -> categories.add(CategoryDto.from(category)));
		return categories;
	}

	@Override
	public CategoryDto deleteByName(String name) {
		Category category = getCategoryByName(name);
		categoryRepository.delete(category);
		return CategoryDto.from(category);
	}

	@Override
	public CategoryDto editCategory(EditCategoryDto dto) {
		checkCategoryName(dto.getNewName());
		Category category = getCategoryByName(dto.getName());
		category.setName(dto.getNewName());
		categoryRepository.save(category);
		return CategoryDto.from(category);
	}

	@Override
	public CategoryDto addNewCategory(CategoryDto dto) {
		checkCategoryName(dto.getName());
		if(categoryRepository.findByName(dto.getName()).isPresent()){
			throw new CategoryAlreadyExistsException();
		}
		return CategoryDto.from(categoryRepository.save(dto.toCategory()));
	}

	private Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
	}

	private Category getCategoryByName(String name) {
		return categoryRepository.findByName(name).orElseThrow(CategoryNotFoundException::new);
	}

	private void checkCategoryName(String name) {
		if(StringUtils.isEmptyOrWhitespaceOnly(name)){
			throw new InvalidCategoryNameException();
		}
	}
}
