package com.skillstorm.ZachKelley.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.skillstorm.ZachKelley.Beans.Expense;
import com.skillstorm.ZachKelley.Beans.ExpenseTicket;
import com.skillstorm.ZachKelley.Handlers.ExpenseHandler;
import com.skillstorm.ZachKelley.Handlers.HomeHandler;
import com.skillstorm.ZachKelley.Handlers.NewTicketHandler;
import com.skillstorm.ZachKelley.Handlers.SortHandler;
import com.skillstorm.ZachKelley.Handlers.TicketHandler;
import com.skillstorm.ZachKelley.Handlers.TicketsHandler;

@WebServlet(urlPatterns="/", loadOnStartup=1)
public class DispatcherServlet extends HttpServlet{
	
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = sessionFactory.openSession();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		StringBuilder requestURI = new StringBuilder(req.getRequestURI());
		requestURI.replace(0, 10, "");
		String baseURI = requestURI.toString();
		System.out.println("GET: " + baseURI);
		
		// Map to handlers
		switch(baseURI) {
		case "home":
			try {
				resp.getWriter().append(new HomeHandler(session).returnHome());
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Tickets":
			try {
				resp.getWriter().append(new TicketsHandler(session).returnTicket(req));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "sort":
			try {
				resp.getWriter().append(new SortHandler(session).returnSort(req.getParameter("sort"), req.getParameter("order")));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("Handler Not Found!");
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder requestURI = new StringBuilder(req.getRequestURI());
		requestURI.replace(0, 10, "");
		String baseURI = requestURI.toString();
		System.out.println("POST: " + baseURI);
		
		// Map to handlers
		switch(baseURI) {
		case "home":
			ExpenseTicket et = new ExpenseTicket(req.getParameter("name"),req.getParameter("notes"));
			try {
				resp.getWriter().append(new HomeHandler(session).postHome(et));
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "expense":
			Expense e = new Expense(req.getParameter("name"),req.getParameter("description"),Double.parseDouble(req.getParameter("cost")));
			System.out.println(e);
			try {
				resp.getWriter().append(new ExpenseHandler(session).postExpense(e, Integer.parseInt(req.getParameter("ticketId"))));
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "newTicket":
			try {
				resp.getWriter().append(new NewTicketHandler(session).postTicket());
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("Handler Not Found!");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder requestURI = new StringBuilder(req.getRequestURI());
		requestURI.replace(0, 10, "");
		String baseURI = requestURI.toString();
		System.out.println("DELETE: " + baseURI);
		
		// Map to handlers
		switch(baseURI) {
		case "home":
			try {
				new HomeHandler(session).deleteHome(Integer.parseInt(req.getParameter("id")));
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Tickets":
			try {
				new TicketsHandler(session).deleteTicket(Integer.parseInt(req.getParameter("id")));
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "expense":
			try {
				resp.getWriter().append(new ExpenseHandler(session).deleteExpense(Integer.parseInt(req.getParameter("expenseId"))));
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("Handler Not Found!");
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder requestURI = new StringBuilder(req.getRequestURI());
		requestURI.replace(0, 10, "");
		String baseURI = requestURI.toString();
		System.out.println("PUT: " + baseURI);
		
		// Map to handlers
		switch(baseURI) {
		case "Tickets":
			try {
				resp.getWriter().append(new TicketsHandler(session).updateTicket(Integer.parseInt(req.getParameter("id")),req.getParameter("status")));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "Ticket":
			try {
				resp.getWriter().append(new TicketHandler(session).updateTicket(Integer.parseInt(req.getParameter("id")),req.getParameter("name"),req.getParameter("department"),req.getParameter("notes")));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "expense":
			try {
				resp.getWriter().append(new ExpenseHandler(session).updateExpense(Integer.parseInt(req.getParameter("expenseId")),req.getParameter("name"),req.getParameter("desc"),Double.parseDouble(req.getParameter("cost"))));
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("Handler Not Found!");
		}
	}
	
}
