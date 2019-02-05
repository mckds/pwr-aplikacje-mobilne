package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.model.Category;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BudgetDto {

	private static final ModelMapper modelMapper = new ModelMapper();
	private Long budgetId;
	@NotNull
	private String name;
	@NotNull
	private LocalDate startDate;
	@NotNull
	private LocalDate endDate;
	@NotNull
	private Double expenditureLimit;
	private List<CategoryDto> categories = new ArrayList<>();

	public static BudgetDto from(Budget budget) {
		BudgetDto dto = modelMapper.map(budget, BudgetDto.class);
		List<CategoryDto> categories = new ArrayList<>();
		budget.getCategories().forEach(c -> categories.add(CategoryDto.from(c)));
		dto.setCategories(categories);
		return dto;
	}

	public Budget toBudget() {
		Budget budget = modelMapper.map(this, Budget.class);
		List<Category> categoryList = new ArrayList<>();
		this.getCategories().forEach(c -> categoryList.add(c.toCategory()));
		budget.setCategories(categoryList);
		return budget;
	}
}
