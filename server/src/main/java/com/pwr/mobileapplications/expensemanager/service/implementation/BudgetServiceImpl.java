package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.dto.BudgetDto;
import com.pwr.mobileapplications.expensemanager.exception.BudgetNotFoundException;
import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {

	private final BudgetRepository budgetRepository;

	@Autowired
	public BudgetServiceImpl(BudgetRepository budgetRepository) {
		this.budgetRepository = budgetRepository;
	}

	@Override
	public BudgetDto findById(Long id) {
		return BudgetDto.from(budgetRepository.findById(id).
				orElseThrow(() -> new BudgetNotFoundException("Budget not found")));
	}

	@Override
	public BudgetDto addNewBudget(BudgetDto budgetDto) {
		return BudgetDto.from(budgetRepository.save(budgetDto.toBudget()));
	}

}
