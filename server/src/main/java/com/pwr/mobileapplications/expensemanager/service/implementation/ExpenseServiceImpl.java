package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.ExpenseDto;
import com.pwr.mobileapplications.expensemanager.exception.BudgetNotFoundException;
import com.pwr.mobileapplications.expensemanager.exception.ExpenseNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Expense;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.repository.ExpenseRepository;
import com.pwr.mobileapplications.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<ExpenseDto> findAll(Long budgetId) {
        List<ExpenseDto> expenses = new ArrayList<>();
        expenseRepository.findAllByBudget_BudgetId(budgetId).forEach(expense -> expenses.add(ExpenseDto.from(expense)));
        return expenses;
    }

    @Override
    public ExpenseDto addNewExpense(ExpenseDto dto, Long budgetId) {
        Expense expense = dto.toExpense();
        expense.setBudget(budgetRepository.findById(budgetId).orElseThrow(BudgetNotFoundException::new));
        return ExpenseDto.from(expenseRepository.save(expense));
    }

    @Override
    public ExpenseDto delete(ExpenseDto dto) {
        Expense expense = expenseRepository.findById(dto.getExpenseId()).orElseThrow(ExpenseNotFoundException::new);
        expenseRepository.delete(expense);
        return ExpenseDto.from(expense);
    }
}
