package com.financialhouse.assignment.reporting.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String FINANCIAL_HOUSE_EMAIL = "demo@financialhouse.io";
	public static final String FINANCIAL_HOUSE_PASSWORD = "cjaiU8CV";

	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (FINANCIAL_HOUSE_EMAIL.equals(email)) {
            return new User(FINANCIAL_HOUSE_EMAIL, FINANCIAL_HOUSE_PASSWORD, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
    }

}