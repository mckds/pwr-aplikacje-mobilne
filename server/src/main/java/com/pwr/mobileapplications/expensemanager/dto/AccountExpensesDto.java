package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.model.Expense;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@RequiredArgsConstructor
public class AccountExpensesDto {
	private static final ModelMapper modelMapper = new ModelMapper();
	private Long accountId;
	private String username;
	private Double expensesValue;


	public static AccountExpensesDto from(Account account) {
		AccountExpensesDto accountDto = new AccountExpensesDto();
		accountDto.setUsername(account.getUsername());
		accountDto.setAccountId(account.getAccountId());
		accountDto.setExpensesValue(account.getExpenses().stream().map(Expense::getAmount).mapToDouble(i -> i).sum());
		return accountDto;
	}
}
