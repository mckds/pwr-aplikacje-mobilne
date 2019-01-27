package com.pwr.mobileapplications.expensemanager.service.implementation;

import com.pwr.mobileapplications.expensemanager.exception.AccountNotFoundException;
import com.pwr.mobileapplications.expensemanager.model.Account;
import com.pwr.mobileapplications.expensemanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;

    @Autowired
    public UserDetailsServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        try {
            Account account = accountService.findByUsername(s);
            return new User(s, account.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("ROLE_USER"));
        } catch (AccountNotFoundException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }

    }
}
