package com.pwr.mobileapplications.expensemanager.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountToBudgetDto {
	private Long accountId;
	private Long budgetId;
}
