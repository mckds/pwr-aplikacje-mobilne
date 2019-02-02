package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.ExpenseDto;
import com.pwr.mobileapplications.expensemanager.exception.BudgetNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.ExpenseNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.InvalidValueException;
import com.pwr.mobileapplications.expensemanager.model.Expense;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.repository.CategoryRepository;
import com.pwr.mobileapplications.expensemanager.repository.ExpenseRepository;
import com.pwr.mobileapplications.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, BudgetRepository budgetRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ExpenseDto> findAll(Long budgetId) {
        List<ExpenseDto> expenses = new ArrayList<>();
        expenseRepository.findAllByBudget_BudgetId(budgetId).forEach(expense -> expenses.add(ExpenseDto.from(expense)));
        return expenses;
    }

    @Override
    public ExpenseDto addNewExpense(ExpenseDto dto, Long budgetId) {
        if(dto.getAmount()<0){
            throw new InvalidValueException("Expense amount cannot be les than 0");
        }
        if(dto.getDate() == null){
            dto.setDate(LocalDate.now());
        }
        Expense expense = dto.toExpense();
        expense.setBudget(budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new));
        return ExpenseDto.from(expenseRepository.save(expense));
    }

    @Override
    public ExpenseDto delete(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(ExpenseNotFoundException::new);
        expenseRepository.delete(expense);
        return ExpenseDto.from(expense);
    }
}
