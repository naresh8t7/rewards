package com.naresh.rewards.service;

import com.naresh.rewards.models.Customer;
import com.naresh.rewards.models.CustomerTransaction;
import com.naresh.rewards.repository.CustomerRepository;
import com.naresh.rewards.repository.CustomerTransactionRepository;
import com.naresh.rewards.response.CustomerRewardsResponse;
import com.naresh.rewards.response.RewardsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RewardsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTransactionRepository transcationRepository;

    public CustomerRewardsResponse getCustomerRewards(long id, String rollUp, int window) {
        // Validate and set defaults for rollUp and window
        if (!rollUp.equalsIgnoreCase("by_day") && !rollUp.equalsIgnoreCase("by_month")) {
            rollUp = "by_month";
        }
        if (rollUp.equalsIgnoreCase("by_day")) {
            if (window < 0 || window > 365) {
                window = 30; // default to one month by day.
            }
        } else if (rollUp.equalsIgnoreCase("by_month")) {
            if (window < 0 || window > 12) {
                window = 3; // default to 3 months.
            }
        }

        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        CustomerRewardsResponse response = new CustomerRewardsResponse();
        Customer cust = customer.get();
        response.setCustomerName(cust.getName());

        // Calculate rewards based on transactions
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        if (rollUp.equalsIgnoreCase("by_month")) {
            c.add(Calendar.MONTH, -1 * window);
        } else {
            c.add(Calendar.DATE, -1 * window);
        }
        List<CustomerTransaction> transactions = transcationRepository.findAllByTransactionDateAfterAndCustomerId(c.getTime(), id);
       

        HashMap<String, RewardsResponse> map = new HashMap<>();
        for (CustomerTransaction t : transactions) {
            SimpleDateFormat formatter;
            if (rollUp.equalsIgnoreCase("by_month")) {
                formatter = new SimpleDateFormat("MMMM");
            } else {
                formatter = new SimpleDateFormat("MM/dd/yyyy");
            }
            String strDate = formatter.format(t.getTransactionDate());
            if (map.containsKey(strDate)) {
                RewardsResponse resp = map.get(strDate);
                resp.setAmount(resp.getAmount() + t.getAmount());
                resp.setPoints(resp.getPoints() + t.getPoints());
                map.put(strDate, resp);
            } else {
                map.put(strDate, new RewardsResponse(strDate, t.getAmount(), t.getPoints()));
            }
        }

        List<RewardsResponse> rewards = new ArrayList<>(map.values());
        response.setRewards(rewards);
        response.setTotalRewardPoints(rewards.stream().mapToInt(RewardsResponse::getPoints).sum());

        return response;
    }
}
