package com.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.enity.Account;
import com.banking.repository.BankRepository;

@Service
public class BankService {
	@Autowired
	BankRepository repository;

//For creating account
	public Account createaccount(Account account) {
		repository.save(account);
		return account;
	}

//For all account
	public List<Account> allaccount() {
		return repository.findAll();
	}

//For Fetching details of account based on id
	public Account getdetails(Integer id) {
		Account a = null;
		Optional<Account> optional = repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return a;
	}

//For deposit to account
	public Account depositbalance(Integer id, int amount) {
		Account account = null;
		Optional<Account> optional = repository.findById(id);
		if (optional.isPresent()) {
			account = optional.get();
			account.setBalance(account.getBalance() + amount);
			return repository.save(account);
		} else
			return account;

	}

//For withdrawel to account
	public Account withdrawel(Integer id, int amount) {
		Account account = null;
		Optional<Account> optional = repository.findById(id);
		if (optional.isPresent()) {
			account = optional.get();
			if (account.getBalance() > amount) {
				account.setBalance(account.getBalance() - amount);
				return repository.save(account);
			} else {
				return account;
			}
		} else
			return account;
	}

//For Delete a account
	public Account deleteaccount(Integer id) {
		Account account = null;
		Optional<Account> optional = repository.findById(id);
		if (optional.isPresent()) {
			account = optional.get();
			repository.deleteById(id);
		}
		return account;
	}

}
