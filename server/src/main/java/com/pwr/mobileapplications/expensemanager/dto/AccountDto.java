package com.pwr.mobileapplications.expensemanager.dto;

import com.pwr.mobileapplications.expensemanager.model.Account;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@RequiredArgsConstructor
public class AccountDto {
	private static final ModelMapper modelMapper = new ModelMapper();
	private Long accountId;
	private String username;


	public static AccountDto from(Account account) {
		return modelMapper.map(account, AccountDto.class);
	}

	public Account toAccount() {
		return modelMapper.map(this, Account.class);
	}
}
