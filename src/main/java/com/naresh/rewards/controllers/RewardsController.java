package com.naresh.rewards.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.rewards.repository.CustomerTransactionRepository;
import com.naresh.rewards.response.CustomerRewardsResponse;
import com.naresh.rewards.service.RewardsService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/customers")
public class RewardsController {

	@Autowired
    private RewardsService rewardsService;

	@Autowired
	CustomerTransactionRepository transcationRepository;

	@GetMapping("/{id}/rewards")
	public ResponseEntity<CustomerRewardsResponse> getCustomerRewards(@PathVariable("id") long id,
			@RequestParam("roll_up") Optional<String> rollUpParam,
			@RequestParam("window") Optional<Integer> windowParam) {

		String rollUp = rollUpParam.orElseGet(() -> "by_month");
		Integer window = windowParam.orElseGet(() -> 3);
		
		CustomerRewardsResponse response = rewardsService.getCustomerRewards(id, rollUp, window);
        return new ResponseEntity<>(response, HttpStatus.OK);

	}

}