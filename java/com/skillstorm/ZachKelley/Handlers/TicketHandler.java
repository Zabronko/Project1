package com.skillstorm.ZachKelley.Handlers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.ZachKelley.Beans.ExpenseTicket;
import com.skillstorm.ZachKelley.Beans.Status;
import com.skillstorm.ZachKelley.DAOs.StatusDAO;
import com.skillstorm.ZachKelley.DAOs.TicketDAO;

public class TicketHandler {
	TicketDAO dao;
	StatusDAO sdao;
	
	public TicketHandler(Session session) throws ClassNotFoundException, SQLException {
		super();
		dao = new TicketDAO(session);
		sdao = new StatusDAO(session);
	}
		
	public String returnTicket(HttpServletRequest req) throws SQLException, ClassNotFoundException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.findById(Integer.parseInt(req.getParameter("id"))));
		ExpenseTicket t = dao.findById(Integer.parseInt(req.getParameter("id")));
		t.setStatus(sdao.findById(t.getStatus().getStatusId()));
		System.out.println(t);
		return json;
	}

	public String updateTicket(int id, String name, String department, String notes) throws SQLException, JsonProcessingException {
		ExpenseTicket e = dao.findById(id);
		e.setName(name);
		e.setDepartment(department);
		e.setNotes(notes);
		dao.update(e);
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.findById(id));
		return json;
	}
}
