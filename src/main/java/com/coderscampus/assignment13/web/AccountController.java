package com.coderscampus.assignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/users/{userId}/accounts")
	public String createNewAccount(@PathVariable Long userId) {
		Account account = accountService.createNewAccount(userId);
		return "redirect:/users/" + userId;
	}
	
	@GetMapping("/users/{userId}/accounts/{accountId}")
	public String showOneAccount(@PathVariable Long userId, @PathVariable Long accountId, ModelMap model) {
		Account account = accountService.findById(accountId);
		User user = userService.findById(userId);
		model.put("account", account);
		model.put("user", user);
		return "account";
	}
	
	@PostMapping("/users/{userId}/accounts/{accountId}")
	public String updateAccountName(@PathVariable Long userId, @PathVariable Long accountId) {
		Account account = accountService.findById(accountId);
		accountService.save(account);
		return "redirect:/users/" + userId + "/accounts/" + accountId;
	}
}

