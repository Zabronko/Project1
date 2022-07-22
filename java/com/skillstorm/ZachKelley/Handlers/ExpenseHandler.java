package com.skillstorm.ZachKelley.Handlers;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.ZachKelley.Beans.Expense;
import com.skillstorm.ZachKelley.Beans.ExpenseTicket;
import com.skillstorm.ZachKelley.DAOs.ExpenseDAO;
import com.skillstorm.ZachKelley.DAOs.TicketDAO;

public class ExpenseHandler {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	ExpenseDAO dao;
	TicketDAO tdao;
	
	public ExpenseHandler(Session session) throws ClassNotFoundException, SQLException {
		super();
		dao = new ExpenseDAO(session);
		tdao = new TicketDAO(session);
	}
	
	public String postExpense(Expense e, int ticketId) throws SQLException, JsonProcessingException {
		e.setTicket(tdao.findById(ticketId));
		dao.saveExpense(e);
		ExpenseTicket et = e.getTicket();
		System.out.println(et.getTotalCost());
		if(et.getExpenses()==null) {
			Set<Expense> nset = new HashSet<Expense>();
			nset.add(e);
			et.setExpenses(nset);
		}else {
			et.getExpenses().add(e);
		}
		et.getExpenses().add(e);
		double totalcost = 0;
		for(Expense t:et.getExpenses()) {
			totalcost += t.getCost();
		}
		et.setTotalCost(Double.parseDouble(df.format(totalcost)));
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(tdao.update(et));
		return json;
	}
	
	public String deleteExpense(int expenseId) throws JsonProcessingException {
		Expense e = dao.findById(expenseId);
		ExpenseTicket et = e.getTicket();
		et.getExpenses().remove(e);
		double totalcost = 0;
		for(Expense t:et.getExpenses()) {
			totalcost += t.getCost();
		}
		et.setTotalCost(Double.parseDouble(df.format(totalcost)));
		dao.delete(e);
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(et);
		return json;
	}
	
	public String updateExpense(int id, String name, String desc, double cost) throws JsonProcessingException {
		Expense e = dao.findById(id);
		e.setName(name);
		e.setDescription(desc);
		e.setCost(cost);
		dao.update(e);
		ExpenseTicket et = tdao.findById(e.getTicket().getId());
		double totalcost = 0;
		for(Expense t:et.getExpenses()) {
			totalcost += t.getCost();
		}
		et.setTotalCost(Double.parseDouble(df.format(totalcost)));
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(tdao.update(et));
		return json;
	}
}
