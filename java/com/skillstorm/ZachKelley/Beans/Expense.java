package com.skillstorm.ZachKelley.Beans;

import java.util.Objects;

public class Expense {
	
	private int expenseId;
	private String name;
	private String notes;
	private int statusId;
	
	
	
	public Expense(int expenseId, String name, String notes, int statusId) {
		super();
		this.expenseId = expenseId;
		this.name = name;
		this.notes = notes;
		this.statusId = statusId;
	}

	public Expense(String name, String notes, int statusId) {
		super();
		this.name = name;
		this.notes = notes;
		this.statusId = statusId;
	}
	
	public Expense(String name) {
		super();
		this.name = name;
	}
	public Expense(String name, String notes) {
		super();
		this.name = name;
		this.notes = notes;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expenseId, name, notes, statusId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		return expenseId == other.expenseId && Objects.equals(name, other.name) && Objects.equals(notes, other.notes)
				&& statusId == other.statusId;
	}

	@Override
	public String toString() {
		return "{expenseId:" + expenseId + ", name:" + name + ", notes:" + notes + ", statusId:" + statusId
				+ "}";
	}
	
}
