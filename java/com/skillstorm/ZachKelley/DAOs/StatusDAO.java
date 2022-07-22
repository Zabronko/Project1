package com.skillstorm.ZachKelley.DAOs;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.skillstorm.ZachKelley.Beans.Status;
import com.skillstorm.ZachKelley.Beans.Status;

public class StatusDAO {
	private Connection connection;

	public Session session;

	public StatusDAO(Session session) {
		super();
		this.session = session;
	}

	public Status findById(int id) {
		Status Object = (Status) session.get(Status.class, id); // must cast
		return Object;
	}

	public List<Status> findByStatus(String like) {
		String hql = "from Status where status=:search";
		Query q = session.createQuery(hql);
		q.setString("search", like);
		System.out.println(q.list());
		return q.list();
	}

	public List<Status> findAll() {
		return session.createQuery("from Status").list();
	}

	public Status saveStatus(Status status) {
		Transaction tx = session.beginTransaction();
		session.save(status);
		tx.commit();
		return status;
	}

	public Boolean delete(Status status) {
		Transaction tx = session.beginTransaction();
		session.delete(status);
		tx.commit();
		return true;
	}

	public void update(Status status) {
		Transaction tx = session.beginTransaction();
		session.update(status);
		tx.commit();
	}
}
