package com.naresh.rewards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.naresh.rewards.models.Customer;
import com.naresh.rewards.models.CustomerTransaction;
import com.naresh.rewards.repository.CustomerRepository;
import com.naresh.rewards.repository.CustomerTransactionRepository;
import com.naresh.rewards.response.CustomerRewardsResponse;

class RewardsServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerTransactionRepository transactionRepository;

    @InjectMocks
    private RewardsService rewardsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerRewards_ByMonth() {
        long customerId = 1;
        String rollUp = "by_month";
        int window = 3;

        Customer customer = new Customer("John Test");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date threeMonthsAgo = calendar.getTime();

        List<CustomerTransaction> transactions = new ArrayList<>();
        CustomerTransaction trans1 = new CustomerTransaction();
        trans1.setId(1);
        trans1.setCustomer(customer);
        trans1.setAmount(200.0f);
        trans1.setTransactionDate(new Date());
        
        CustomerTransaction trans2 = new CustomerTransaction();
        trans2.setId(2);
        trans2.setCustomer(customer);
        trans2.setAmount(50.0f);
        trans2.setTransactionDate(threeMonthsAgo);
        
        transactions.add(trans1);
        transactions.add(trans2);
        when(transactionRepository.findAllByTransactionDateAfterAndCustomerId(any(), eq(customerId)))
        .thenReturn(transactions);

        CustomerRewardsResponse response = rewardsService.getCustomerRewards(customerId, rollUp, window);
        assertNotNull(response);
        assertEquals("John Test", response.getCustomerName());
        assertEquals(2, response.getRewards().size());
        assertEquals(250, response.getTotalRewardPoints());
    }

    @Test
    void testGetCustomerRewards_ByDay() {
        long customerId = 1;
        String rollUp = "by_day";
        int window = 30;

        Customer customer = new Customer("John Test");
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        Date thirtyDaysAgo = calendar.getTime();

        List<CustomerTransaction> transactions = new ArrayList<>();
        CustomerTransaction trans1 = new CustomerTransaction();
        trans1.setId(1);
        trans1.setCustomer(customer);
        trans1.setAmount(200.0f);
        trans1.setTransactionDate(new Date());
        
        CustomerTransaction trans2 = new CustomerTransaction();
        trans2.setId(2);
        trans2.setCustomer(customer);
        trans2.setAmount(50.0f);
        trans2.setTransactionDate(thirtyDaysAgo);
        transactions.add(trans1);
        transactions.add(trans2);
        when(transactionRepository.findAllByTransactionDateAfterAndCustomerId(thirtyDaysAgo, customerId))
                .thenReturn(transactions);

        CustomerRewardsResponse response = rewardsService.getCustomerRewards(customerId, rollUp, window);
        assertNotNull(response);
        assertEquals("John Test", response.getCustomerName());
        assertEquals(2, response.getRewards().size());
        assertEquals(250, response.getTotalRewardPoints());
    }

    @Test
    void testGetCustomerRewards_CustomerNotFound() {
        long customerId = 999;
        String rollUp = "by_month";
        int window = 3;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> rewardsService.getCustomerRewards(customerId, rollUp, window));
    }
}
