package com.banking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.banking.enity.Account;
import com.banking.service.BankService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/banking")
public class BankController {
	@Autowired
	BankService bankService;

	// For creating account
	@PostMapping("/create")
	public ResponseEntity<?> CreateAccount(@RequestBody Account account) {
		ResponseEntity<?> entity = null;
		if (account != null) {
			Account account2 = bankService.createaccount(account);
			entity = new ResponseEntity<Account>(account2, HttpStatus.OK);
		} else
			entity = new ResponseEntity<String>("Request Failed", HttpStatus.OK);
		return entity;
	}

	// For all account
	@GetMapping("/all")
	public List<Account> getAllAccount() {

		return bankService.allaccount();
	}

	// For Fetching details of account based on id
	@GetMapping("/getdetails")
	public ResponseEntity<?> Getdetails(@RequestParam Integer Id) {
		ResponseEntity<?> entity = null;
		Account accountd = bankService.getdetails(Id);
		if (accountd != null) {
			entity = new ResponseEntity<Account>(accountd, HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("Account not found", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// For deposit to account
	@PutMapping("deposit/{id}/{amount}")
	public ResponseEntity<?> deposit(@PathVariable Integer id, @PathVariable int amount) {
		ResponseEntity<?> entity = null;
		Account accountdeposit = bankService.depositbalance(id, amount);
		if (accountdeposit != null) {
			entity = new ResponseEntity<Account>(accountdeposit, HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("Account Not found", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	// For withdrawel to account

	@PutMapping("withdrawel/{id}/{amount}")
	public ResponseEntity<?> withdrawel(@PathVariable Integer id, @PathVariable int amount) {
		ResponseEntity<?> entity = null;
		Account accountwithdrwel = bankService.withdrawel(id, amount);
		if (accountwithdrwel != null) {
			if (accountwithdrwel.getBalance() > amount) {
				entity = new ResponseEntity<Account>(accountwithdrwel, HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("Insuffiecient Funds", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			entity = new ResponseEntity<String>("Account not found", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//For delete account
	@DeleteMapping("/delete")
	public ResponseEntity<?>Deleteaccount(@RequestParam Integer id){
	ResponseEntity<?>entity=null;
	Account accountdelete= bankService.deleteaccount(id);
	if(accountdelete!= null) {
		entity= new ResponseEntity<String>("Account is deleted successfully",HttpStatus.OK);
	}else {
		entity= new ResponseEntity<String>("Account not Found",HttpStatus.BAD_REQUEST);
	}
	return entity;
		
	}

}
