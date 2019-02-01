package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.model.Category;

import java.util.Optional;

class CategoryMother {

	static Category getCategory(){
		Category category = new Category();
		category.setName("Food");
		return category;
	}
	static Optional<Category> getOptionalCategory(){
		return Optional.of(getCategory());
	}

}
