package com.skillstorm.ZachKelley.Handlers;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.ZachKelley.Beans.Expense;
import com.skillstorm.ZachKelley.jdbc.ExpenseDAO;

public class HomeHandler {
	ExpenseDAO dao;
	
	public HomeHandler() throws ClassNotFoundException, SQLException {
		dao = new ExpenseDAO();
	}
	
	public String returnHome() throws SQLException, ClassNotFoundException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.findAll());
		return json;
	}
	
	public String postHome(Expense e) throws SQLException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.create(e));
		return json;
	}
	
	public Boolean deleteHome(int index) throws SQLException {
		return dao.delete(index);
	}
}
