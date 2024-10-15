package com.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.enity.Account;
@Repository
public interface BankRepository extends JpaRepository<Account, Integer> {

	
}
