package com.naresh.rewards.response;

import java.util.List;

public class CustomerRewardsResponse {

	private String customerName;
	private float totalRewardPoints;
	private List<RewardsResponse> rewards;

	public CustomerRewardsResponse() {
	}

	public CustomerRewardsResponse(String customerName, float totalRewardPoints, List<RewardsResponse> rewards) {
		super();
		this.customerName = customerName;
		this.totalRewardPoints = totalRewardPoints;
		this.rewards = rewards;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public float getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(float totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}

	public List<RewardsResponse> getRewards() {
		return rewards;
	}

	public void setRewards(List<RewardsResponse> rewards) {
		this.rewards = rewards;
	}

}