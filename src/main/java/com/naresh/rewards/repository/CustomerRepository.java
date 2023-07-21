package com.naresh.rewards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naresh.rewards.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	List<Customer> findByName(String name);
}