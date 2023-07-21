package com.naresh.rewards.response;

public class RewardsResponse {

	private String rollUpId; // date when rollup by by_day, month by by_month
	private float amount;
	private int points;

	public RewardsResponse() {
	}

	public RewardsResponse(String rollUpId, float amount, int points) {
		super();
		this.rollUpId = rollUpId;
		this.amount = amount;
		this.points = points;
	}

	public String getRollUpId() {
		return rollUpId;
	}

	public void setRollUpId(String rollUpId) {
		this.rollUpId = rollUpId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}