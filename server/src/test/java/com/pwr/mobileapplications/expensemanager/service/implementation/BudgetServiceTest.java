package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.repository.BudgetRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BudgetServiceTest {

	@Mock
	BudgetRepository budgetRepository;

	@Before
	public void setup(){

		when(budgetRepository.findById(1L)).thenReturn(BudgetMother.budgetAsOptional());
	}

}