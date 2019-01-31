package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import com.pwr.mobileapplications.expensemanager.service.BudgetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BudgetServiceTest {

	@Mock
	BudgetRepository budgetRepository;

	BudgetService budgetService;

	@Before
	public void setup(){
		budgetService = new BudgetServiceImpl(budgetRepository);
		when(budgetRepository.findById(1L)).thenReturn(BudgetMother.budgetAsOptional());
	}

	@Test
	public void addAccountTest(){

	}

}