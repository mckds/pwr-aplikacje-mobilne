package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping()
	public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid NewCategoryDto dto){
		return new ResponseEntity<>(categoryService.addNewCategory(dto), HttpStatus.CREATED);
	}

	@DeleteMapping()
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id, @RequestBody CategoryDto dto){
		return new ResponseEntity<>(categoryService.deleteByNameAndBudgetId(dto.getName(), id), HttpStatus.NO_CONTENT);
	}
}
