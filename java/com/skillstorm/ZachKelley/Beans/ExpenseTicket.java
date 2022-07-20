package com.skillstorm.ZachKelley.Beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity()
@Table(name="expenseticket")
public class ExpenseTicket {

	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="department")
	private String department;
	
	@Column(name="Notes")
	private String notes;
	
	@Column(name="total_cost")
	private double totalCost;
	
	@OneToMany(mappedBy="ticket", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Expense> expenses;
	
	@OneToOne()
	@JoinColumn(name="status_id")
	private Status status;
	
	public ExpenseTicket() {
		super();
	}
	
	public ExpenseTicket(String name, String notes) {
		super();
		this.name = name;
		this.notes = notes;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", department:" + department + ", notes:" + notes
				+ ", totalCost:" + totalCost + ", status:" + status + ", expenses:" + expenses + "}";
	}
	
	
	
}
