package com.skillstorm.ZachKelley.DAOs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.skillstorm.ZachKelley.Beans.ExpenseTicket;

public class TicketDAO {

	public Session session;

	public TicketDAO(Session session) {
		super();
		this.session = session;
	}
	
	public ExpenseTicket findById(int id) {
		ExpenseTicket Object = (ExpenseTicket) session.get(ExpenseTicket.class, id); //must cast
		return Object;
	}
	
	public List<ExpenseTicket> findByName(String like) {
		String hql = "from ExpenseTicket where name like :search";
		Query q = session.createQuery(hql);
		q.setString("search", "%"+like+"%");
		return q.list();
	}
	
	public List<ExpenseTicket> findByNameCriteria(String like) {
		Criteria criteria = session.createCriteria(ExpenseTicket.class);
		criteria.add(Restrictions.like("name", "%"+like+"%"));
		return criteria.list(); //criteria.uniqueResult();
	}
	
	public List<ExpenseTicket> findAll() {
		return session.createQuery("from ExpenseTicket").list();
	}
	
	public ExpenseTicket saveTicket(ExpenseTicket ticket) {
		Transaction tx = session.beginTransaction();
		session.save(ticket);
		tx.commit();
		return ticket;
	}
	
	public Boolean delete(ExpenseTicket ticket) {
		Transaction tx = session.beginTransaction();
		session.delete(ticket);
		tx.commit();
		return true;
	}
	
	public void update(ExpenseTicket ticket) {
		Transaction tx = session.beginTransaction();
		session.update(ticket);
		tx.commit();
	}
}
