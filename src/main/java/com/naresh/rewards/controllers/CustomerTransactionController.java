package com.naresh.rewards.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.rewards.models.CustomerTransaction;
import com.naresh.rewards.repository.CustomerTransactionRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/customers")
public class CustomerTransactionController {

	@Autowired
	CustomerTransactionRepository customerTransactionRepository;

	@GetMapping("/transactions")
	public ResponseEntity<List<CustomerTransaction>> getAllCustomerTransactions() {
		try {
			List<CustomerTransaction> transactions = new ArrayList<CustomerTransaction>();
			customerTransactionRepository.findAll().forEach(transactions::add);
			if (transactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}/transactions")
	public ResponseEntity<List<CustomerTransaction>> getCustomerTransactionById(@PathVariable("id") long id) {
		List<CustomerTransaction> transactions = customerTransactionRepository.findAllByCustomerId(id);

		if (transactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

}