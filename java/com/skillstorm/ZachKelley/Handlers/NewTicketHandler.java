package com.skillstorm.ZachKelley.Handlers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.ZachKelley.Beans.ExpenseTicket;
import com.skillstorm.ZachKelley.DAOs.StatusDAO;
import com.skillstorm.ZachKelley.DAOs.TicketDAO;

public class NewTicketHandler {

	TicketDAO dao;
	StatusDAO sdao;
	
	public NewTicketHandler(Session session) throws ClassNotFoundException, SQLException {
		super();
		dao = new TicketDAO(session);
		sdao = new StatusDAO(session);
	}
		
	public String postTicket() throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		ExpenseTicket et = new ExpenseTicket("","");
		et.setStatus(sdao.findById(1));
		String json = om.writeValueAsString(dao.saveTicket(et));
		System.out.println(json);
		return json;
	}
}
