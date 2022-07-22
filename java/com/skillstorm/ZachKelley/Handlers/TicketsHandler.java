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

public class TicketsHandler {
	TicketDAO dao;
	StatusDAO sdao;
	
	public TicketsHandler(Session session) throws ClassNotFoundException, SQLException {
		super();
		dao = new TicketDAO(session);
		sdao = new StatusDAO(session);
	}
	
	public String returnTicket(HttpServletRequest req) throws SQLException, ClassNotFoundException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		System.out.println(req.getParameter("id"));
		ExpenseTicket t = dao.findById(Integer.parseInt(req.getParameter("id")));
		t.setStatus(sdao.findById(t.getStatus().getStatusId()));
		String json = om.writeValueAsString(dao.update(t));
		return json;
	}
	
	public String postTicket(ExpenseTicket e) throws SQLException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(dao.saveTicket(e));
		return json;
	}
	
	public Boolean deleteTicket(int index) throws SQLException {
		return dao.delete(dao.findById(index));
	}
	
	public String updateTicket(int id, String status) throws SQLException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		ExpenseTicket e = dao.findById(id);
		List<Status> statuses = sdao.findByStatus(status);
		if(statuses.size() > 0) {
			e.setStatus(statuses.get(0));
			dao.update(e);
			String json = om.writeValueAsString(dao.findAll());
			return json;
		}else {
			return "";
		}
	}
}
