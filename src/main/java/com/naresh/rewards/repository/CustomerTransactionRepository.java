package com.naresh.rewards.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naresh.rewards.models.CustomerTransaction;

import io.micrometer.core.annotation.Timed;

public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {

	@Timed(value = "find.transaction.customer.id", description = "Time taken to fetch transactions by customer id")
	List<CustomerTransaction> findAllByCustomerId(long id);

	@Timed(value = "find.transaction.customer.id.date", description = "Time taken to fetch transactions by customer id and date")
	List<CustomerTransaction> findAllByTransactionDateAfterAndCustomerId(Date transactionDate, long id);
}