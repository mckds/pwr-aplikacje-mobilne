package com.pwr.mobileapplications.expensemanager.repository;

import com.pwr.mobileapplications.expensemanager.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findAllByBudget_BudgetId(Long budgetId);
	Optional<Category> findByNameAndBudget_BudgetId(String name, Long budgetId);
	List<Category> findByBudget_BudgetId(Long budgetId);
	Optional<Category> deleteByNameAndBudget_BudgetId(String name, Long id);
}
