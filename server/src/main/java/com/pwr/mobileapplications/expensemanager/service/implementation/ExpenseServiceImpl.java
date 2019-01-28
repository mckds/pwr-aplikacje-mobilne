package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.exception.ExpenseNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Expense;
import com.pwr.mobileapplications.expensemanager.repository.ExpenseRepository;
import com.pwr.mobileapplications.expensemanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense save(Expense element) {
        return expenseRepository.save(element);
    }

    @Override
    public boolean deleteById(Long id) {
        if(!expenseRepository.existsById(id)) {
            return false;
        }

        expenseRepository.deleteById(id);
        return true;
    }
}
