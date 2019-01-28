package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.exception.BudgetNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget findById(Long id) {
        return budgetRepository.findById(id).orElseThrow(() -> new BudgetNotFoundException("Budget not found"));
    }

    @Override
    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget save(Budget element) {
        return budgetRepository.save(element);
    }

    @Override
    public boolean deleteById(Long id) {
        if(!budgetRepository.existsById(id)) {
            return false;
        }

        budgetRepository.deleteById(id);
        return true;
    }
}
