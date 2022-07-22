package com.skillstorm.ZachKelley.Handlers;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.ZachKelley.Beans.ExpenseTicket;
import com.skillstorm.ZachKelley.DAOs.TicketDAO;

public class HomeHandler {
	TicketDAO dao;
	
	public HomeHandler(Session session) throws ClassNotFoundException, SQLException {
		super();
		dao = new TicketDAO(session);
	}
	
	public String returnHome() throws SQLException, ClassNotFoundException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.findAll());
		return json;
	}
	
	public String postHome(ExpenseTicket e) throws SQLException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.saveTicket(e));
		return json;
	}
	
	public Boolean deleteHome(int index) throws SQLException {
		return dao.delete(dao.findById(index));
	}
}
