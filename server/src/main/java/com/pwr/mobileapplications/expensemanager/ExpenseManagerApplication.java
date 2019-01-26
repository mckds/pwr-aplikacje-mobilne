package com.pwr.mobileapplications.expensemanager;

import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.model.Category;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootApplication
public class ExpenseManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseManagerApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(AccountRepository accountRepository, BudgetRepository budgetRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			Account account = new Account("user", "user");
			account = accountRepository.save(account);

			Budget budget = new Budget(LocalDate.now(), LocalDate.now(), 1000.0);
			budget = budgetRepository.save(budget);


			account.setBudget(budget);
			account = accountRepository.save(account);
			budget.getAccounts().add(account);
			budget = budgetRepository.save(budget);

			Category category = new Category("expenses", budget);
			categoryRepository.save(category);
			budget.getCategories().add(category);
			budget = budgetRepository.save(budget);

		};
	}
}

