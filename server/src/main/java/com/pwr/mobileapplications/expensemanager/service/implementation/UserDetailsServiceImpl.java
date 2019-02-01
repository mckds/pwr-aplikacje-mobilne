package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;

	@Autowired
	public UserDetailsServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) {

		Account account = null;
		try {
			account = accountRepository.findByUsername(userName).orElseThrow(AccountNotFoundException::new);
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}
		return new User(account.getUsername(), account.getPassword(),
					true, true, true, true,
					AuthorityUtils.createAuthorityList("ROLE_USER"));
	}
}
