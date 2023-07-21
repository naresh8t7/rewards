/**
 * 
 */
package com.naresh.rewards.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.naresh.rewards.response.CustomerRewardsResponse;
import com.naresh.rewards.service.RewardsService;

class RewardsControllerTest {

    @Mock
    private RewardsService rewardsService;

    @InjectMocks
    private RewardsController rewardsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerRewards() {
        long customerId = 1;
        String rollUp = "by_month";
        int window = 3;

        CustomerRewardsResponse response = new CustomerRewardsResponse();
        response.setCustomerName("John Test");
        when(rewardsService.getCustomerRewards(customerId, rollUp, window)).thenReturn(response);

        ResponseEntity<CustomerRewardsResponse> expectedResponse = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<CustomerRewardsResponse> actualResponse = rewardsController.getCustomerRewards(customerId, Optional.of(rollUp), Optional.of(window));

        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
    }
}
