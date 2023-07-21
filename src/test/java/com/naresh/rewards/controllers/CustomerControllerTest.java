/**
 * 
 */
package com.naresh.rewards.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.naresh.rewards.models.Customer;
import com.naresh.rewards.repository.CustomerRepository;

/**
 * 
 */
class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer("Test 1");
        Customer customer2 = new Customer("Test 2");
        List<Customer> customers = List.of(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customers);

        ResponseEntity<List<Customer>> responseEntity = customerController.getAllCustomers(null);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customers, responseEntity.getBody());
    }

    @Test
    void testGetCustomers_ByName() {
        String name = "John Test";
        Customer customer1 = new Customer(name);
        List<Customer> customers = List.of(customer1);
        when(customerRepository.findByName(name)).thenReturn(customers);

        ResponseEntity<List<Customer>> responseEntity = customerController.getAllCustomers(name);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customers, responseEntity.getBody());
    }


    @Test
    void testGetCustomersById_ExistingCustomer() {
        long customerId = 1;
        Customer customer = new Customer("John Test");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> responseEntity = customerController.getCustomerById(customerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(customer, responseEntity.getBody());
    }

    @Test
    void testGetCustomersById_CustomerNotFound() {
        long customerId = 999;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> customerController.getCustomerById(customerId));
    }
}
