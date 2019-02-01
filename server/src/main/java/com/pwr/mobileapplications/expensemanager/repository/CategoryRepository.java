package com.pwr.mobileapplications.expensemanager.repository;

import com.pwr.mobileapplications.expensemanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String name);
}
