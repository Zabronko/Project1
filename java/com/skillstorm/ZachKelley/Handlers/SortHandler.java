package com.skillstorm.ZachKelley.Handlers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.ZachKelley.Beans.ExpenseTicket;
import com.skillstorm.ZachKelley.DAOs.StatusDAO;
import com.skillstorm.ZachKelley.DAOs.TicketDAO;

public class SortHandler {

	TicketDAO dao;
	
	public SortHandler(Session session) throws ClassNotFoundException, SQLException {
		super();
		dao = new TicketDAO(session);
	}
	
	public String returnSort(String sort, String order) throws SQLException, ClassNotFoundException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.findBySort(sort, order));
		return json;
	}
}
