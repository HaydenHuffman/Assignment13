package com.coderscampus.assignment13.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private UserService userService;

	public Account findById(Long accountId) {
		Optional<Account> accountOpt  = accountRepo.findById(accountId);
		return accountOpt.orElse(new Account());
	}

	public Account createNewAccount(Long userId) {
		Account account = new Account();
		User user = userService.findById(userId);
		user.getAccounts().add(account);
		account.getUsers().add(user);
		account.setAccountName("Account #" + user.getAccounts().size());
		accountRepo.save(account);
		return account;
	}

	public Account save(Account account) {
		return accountRepo.save(account);
		
	}
}
