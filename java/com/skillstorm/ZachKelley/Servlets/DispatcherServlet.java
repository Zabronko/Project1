package com.skillstorm.ZachKelley.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.skillstorm.ZachKelley.Beans.Expense;
import com.skillstorm.ZachKelley.Handlers.HomeHandler;

@WebServlet(urlPatterns="/", loadOnStartup=1)
public class DispatcherServlet extends HttpServlet{
	
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
				resp.getWriter().append(new HomeHandler().returnHome());
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
			Expense e = new Expense(req.getParameter("name"),req.getParameter("notes"));
			System.out.println(e);
			try {
				resp.getWriter().append(new HomeHandler().postHome(e));
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
				new HomeHandler().deleteHome(Integer.parseInt(req.getParameter("id")));
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("Handler Not Found!");
		}
	}
	
}
