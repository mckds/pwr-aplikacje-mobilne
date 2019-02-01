package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.NewCategoryDto;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/budgets/{id}/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public ResponseEntity<List<CategoryDto>> getAll(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.findAll(id));
	}

	@PutMapping()
	public ResponseEntity<CategoryDto> editCategoryName(@PathVariable Long id, @RequestBody @Valid EditCategoryDto dto){
		dto.setBudgetId(id);
		return ResponseEntity.ok(categoryService.editCategory(dto));
	}

	@PostMapping()
	public ResponseEntity<CategoryDto> addCategory(@PathVariable Long id, @RequestBody NewCategoryDto dto){
		dto.setBudgetId(id);
		return ResponseEntity.ok(categoryService.addNewCategory(dto));
	}

	@DeleteMapping()
	public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id, @RequestBody CategoryDto dto){
		return ResponseEntity.ok(categoryService.deleteByNameAndBudgetId(dto.getName(), id));
	}
}
