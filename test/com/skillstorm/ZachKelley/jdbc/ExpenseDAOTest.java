package com.skillstorm.ZachKelley.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.skillstorm.ZachKelley.Beans.Expense;

public class ExpenseDAOTest {
	
	static ExpenseDAO dao;
	
	@BeforeClass
	public static void setup() throws ClassNotFoundException {
		try {
			dao = new ExpenseDAO();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void create() {
		try {
			Expense expense = dao.create(new Expense("TEEESSSSSTTTTT", "Teeessstt"));
			System.out.println(expense.getExpenseId());
		}catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void findAll() {
		try {
			Set<Expense> expenses = dao.findAll();
			System.out.println(expenses.size());
			assertTrue(expenses.size() > 0);
		}catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	@Ignore
	public void delete() {
		try {
			boolean deleted = dao.delete(4);
			System.out.println(deleted);
			assertTrue(deleted);
		}catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void update() {
		Expense expense = new Expense(1, "Test Name", "Test notes", 2);
		try {
			boolean sucess = dao.Update(expense);
			System.out.println(sucess);
			assertEquals(true, sucess);
		}catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	
}
