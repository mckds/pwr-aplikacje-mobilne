package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.model.Budget;
import com.pwr.mobileapplications.expensemanager.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountDto {
	private static final ModelMapper modelMapper = new ModelMapper();
	private Long id;
	private String username;
	private Budget account;
	private List<Expense> expenses;


	public static AccountDto from(Account account) {
		return modelMapper.map(account, AccountDto.class);
	}

	public Account toAccount() {
		return modelMapper.map(this, Account.class);
	}
}
