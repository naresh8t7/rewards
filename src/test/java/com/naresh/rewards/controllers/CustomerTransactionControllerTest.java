/**
 * 
 */
package com.naresh.rewards.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.naresh.rewards.models.Customer;
import com.naresh.rewards.models.CustomerTransaction;
import com.naresh.rewards.repository.CustomerTransactionRepository;

/**
 * 
 */
class CustomerTransactionControllerTest {

    @Mock
    private CustomerTransactionRepository customerTransactionRepository;

    @InjectMocks
    private CustomerTransactionController customerTransactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomerTransactions() {
    	Customer customer1 = new Customer("Test 1");
    	List<CustomerTransaction> transactions = new ArrayList<>();
        CustomerTransaction trans1 = new CustomerTransaction();
        trans1.setId(1);
        trans1.setCustomer(customer1);
        trans1.setAmount(200.0f);
        trans1.setTransactionDate(new Date());
        
        CustomerTransaction trans2 = new CustomerTransaction();
        trans2.setId(2);
        trans2.setCustomer(customer1);
        trans2.setAmount(50.0f);
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        Date thirtyDaysAgo = calendar.getTime();
        
        trans2.setTransactionDate(thirtyDaysAgo);
        transactions.add(trans1);
        transactions.add(trans2);
        when(customerTransactionRepository.findAll()).thenReturn(transactions);

        ResponseEntity<List<CustomerTransaction>> responseEntity = customerTransactionController.getAllCustomerTransactions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactions, responseEntity.getBody());
    }


    @Test
    void testGetCustomerTransactionById() {
        long customerId = 1;
        Customer customer1 = new Customer("Test 1");
        List<CustomerTransaction> transactions = new ArrayList<>();
        CustomerTransaction trans1 = new CustomerTransaction();
        trans1.setId(1);
        trans1.setCustomer(customer1);
        trans1.setAmount(200.0f);
        trans1.setTransactionDate(new Date());
        
        CustomerTransaction trans2 = new CustomerTransaction();
        trans2.setId(2);
        trans2.setCustomer(customer1);
        trans2.setAmount(50.0f);
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        Date thirtyDaysAgo = calendar.getTime();
        
        trans2.setTransactionDate(thirtyDaysAgo);
        transactions.add(trans1);
        transactions.add(trans2);
        when(customerTransactionRepository.findAllByCustomerId(customerId)).thenReturn(transactions);

        ResponseEntity<List<CustomerTransaction>> responseEntity = customerTransactionController.getCustomerTransactionById(customerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactions, responseEntity.getBody());
    }
}
