package com.skillstorm.ZachKelley.Handlers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.ZachKelley.Beans.ExpenseTicket;
import com.skillstorm.ZachKelley.DAOs.TicketDAO;

public class TicketHandler {
	TicketDAO dao;
	
	public TicketHandler(Session session) throws ClassNotFoundException, SQLException {
		super();
		dao = new TicketDAO(session);
	}
	
	public String returnHome(HttpServletRequest req) throws SQLException, ClassNotFoundException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.findById(Integer.parseInt(req.getParameter("id"))));
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
