package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Budget;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class BudgetDto {

	private static final ModelMapper modelMapper = new ModelMapper();
	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double expenditureLimit;

	public static BudgetDto from(Budget budget) {
		return modelMapper.map(budget, BudgetDto.class);
	}

	public Budget toBudget() {
		return modelMapper.map(this, Budget.class);
	}
}
