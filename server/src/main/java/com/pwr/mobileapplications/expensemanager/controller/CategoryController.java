package com.pwr.mobileapplications.expensemanager.controller;

import com.pwr.mobileapplications.expensemanager.dto.CategoryDto;
import com.pwr.mobileapplications.expensemanager.dto.EditCategoryDto;
import com.pwr.mobileapplications.expensemanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping()
	public ResponseEntity<List<CategoryDto>> getAll(){
		return ResponseEntity.ok(categoryService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getById(@PathVariable Long id){
		return ResponseEntity.ok(categoryService.findById(id));
	}

	@PutMapping()
	public ResponseEntity<CategoryDto> editCategoryName(@RequestBody @Valid EditCategoryDto dto){
		return ResponseEntity.ok(categoryService.editCategory(dto));
	}

	@PostMapping()
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto dto){
		return ResponseEntity.ok(categoryService.addNewCategory(dto));
	}

	@DeleteMapping()
	public ResponseEntity<CategoryDto> deleteCategory(@RequestBody CategoryDto dto){
		return ResponseEntity.ok(categoryService.deleteByName(dto.getName()));
	}
}
