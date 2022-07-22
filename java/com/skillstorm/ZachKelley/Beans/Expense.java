package com.skillstorm.ZachKelley.Beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity()
@Table(name="Expense")
public class Expense {
	
	@Id
	@Column(name="expense_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expenseId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="cost")
	private double cost;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="ticket_id")
	@JsonBackReference
	private ExpenseTicket ticket;
	
	
	public Expense(String name, String desc, double cost) {
		super();
		this.name = name;
		this.description = desc;
		this.cost = cost;
	}
	
	public Expense(String name) {
		super();
		this.name = name;
	}
	
	public Expense() {
		super();
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String notes) {
		this.description = notes;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public ExpenseTicket getTicket() {
		return ticket;
	}

	public void setTicket(ExpenseTicket ticket) {
		this.ticket = ticket;
	}
	

	@Override
	public String toString() {
		return "{expenseId:" + expenseId + ", name:" + name + ", description:" + description
				+ ", cost:" + cost + "}";
	}
	
}
