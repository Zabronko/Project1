package com.skillstorm.ZachKelley.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.skillstorm.ZachKelley.Beans.Expense;

public class ExpenseDAO {
	private Connection connection;
	
	//TODO
	//rework
	
	public ExpenseDAO() throws SQLException, ClassNotFoundException {
		super();
		String url = "jdbc:mysql://localhost:3306/expensereimbersement";
		String username = "root";
		String password = "root";
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(url,username,password);
	}
	
	
	public Expense create(Expense expense) throws SQLException {
		String sql = "insert into expense(name, description) values(?,?);";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, expense.getName());
		statement.setString(2, expense.getDescription());
		
		statement.executeUpdate();
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		expense.setExpenseId(rs.getInt(1));
		
		return expense;
	}
	
	public Set<Expense> findAll() throws SQLException {
		String sql = "select * from expense;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		Set<Expense> expenses = new HashSet<Expense>();
		while(rs.next()) {
			Expense row = new Expense(rs.getInt("expense_id"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getInt("status_id"));
			expenses.add(row);
		}
		return expenses;
	}
	
	public boolean delete(int id) throws SQLException {
		String sql = "delete from expense where expense_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		
		return statement.executeUpdate() > 0;
	}
	
	public boolean Update(Expense expense) throws SQLException {
		String sql = "Update expense set name=?,notes=? where expense_id=?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, expense.getName());
		statement.setString(2, expense.getDescription());
		statement.setInt(3, expense.getExpenseId());
		
		return statement.executeUpdate() > 0;
	}
	
}
