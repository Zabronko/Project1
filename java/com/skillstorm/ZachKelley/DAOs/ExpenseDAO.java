package com.skillstorm.ZachKelley.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.skillstorm.ZachKelley.Beans.Expense;
import com.skillstorm.ZachKelley.Beans.Expense;

public class ExpenseDAO {
	public Session session;

	public ExpenseDAO(Session session) {
		super();
		this.session = session;
	}
	
	public Expense findById(int id) {
		Expense Object = (Expense) session.get(Expense.class, id); //must cast
		return Object;
	}
	
	public List<Expense> findByName(String like) {
		String hql = "from Expense where name like :search";
		Query q = session.createQuery(hql);
		q.setString("search", "%"+like+"%");
		return q.list();
	}
	
	public List<Expense> findAll() {
		return session.createQuery("from Expense").list();
	}
	
	public Expense saveExpense(Expense expense) {
		Transaction tx = session.beginTransaction();
		session.save(expense);
		tx.commit();
		return expense;
	}
	
	public Boolean delete(Expense expense) {
		Transaction tx = session.beginTransaction();
		session.delete(expense);
		tx.commit();
		return true;
	}
	
	public Expense update(Expense expense) {
		Transaction tx = session.beginTransaction();
		session.update(expense);
		tx.commit();
		return expense;
	}
	
}
