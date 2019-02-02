package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Expense;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ExpenseDto {
	private static final ModelMapper modelMapper = new ModelMapper();

	private Long expenseId;
	private double amount;
	private LocalDate date;
	private CategoryDto category;
	private AccountDto account;
	private BudgetDto budget;

	public static ExpenseDto from(Expense expense) {
		ExpenseDto dto = modelMapper.map(expense, ExpenseDto.class);
		dto.setCategory(CategoryDto.from(expense.getCategory()));
		dto.setAccount(AccountDto.from(expense.getAccount()));
		dto.setBudget(BudgetDto.from(expense.getBudget()));
		return dto;
	}

	public Expense toExpense() {
		Expense expense = modelMapper.map(this, Expense.class);
		expense.setAccount(this.account.toAccount());
		expense.setCategory(this.category.toCategory());
		expense.setBudget(this.budget.toBudget());
		return expense;
	}

}
