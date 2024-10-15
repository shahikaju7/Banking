package com.banking.enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="Account")
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer Id;
private String name;
private String accounttype;
private int balance;
public Account(String name, String accounttype, int balance) {
	super();
	this.name = name;
	this.accounttype = accounttype;
	this.balance = balance;
}


}
