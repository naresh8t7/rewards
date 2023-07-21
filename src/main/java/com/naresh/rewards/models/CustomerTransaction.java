package com.naresh.rewards.models;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "transactions")
public class CustomerTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "notes")
	private String notes;

	@Column(name = "transaction_date")
	@Temporal(TemporalType.DATE)
	private Date transactionDate;

	@Column(name = "amount")
	private float amount;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Transient
	private int points;

	public CustomerTransaction() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getPoints() {
		int pts = 0;
		if (this.amount > 50 && this.amount <= 100) {
			pts += Math.round(this.amount) - 50;

		} else if (this.amount > 100) {
			pts += (Math.round(amount) - 100) * 2 + 50;

		}
		return pts;
	}

	@Override
	public String toString() {
		return "Tansaction [id=" + id + ", amount=" + amount + ", date=" + transactionDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerTransaction other = (CustomerTransaction) obj;
		return id == other.id;
	}

}