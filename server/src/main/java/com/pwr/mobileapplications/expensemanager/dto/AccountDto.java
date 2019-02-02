package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.model.Expense;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@RequiredArgsConstructor
public class AccountDto {
	private static final ModelMapper modelMapper = new ModelMapper();
	private Long accountId;
	private String username;
	private List<Expense> expenses;


	public static AccountDto from(Account account) {
		return modelMapper.map(account, AccountDto.class);
	}

	public Account toAccount() {
		return modelMapper.map(this, Account.class);
	}
}
