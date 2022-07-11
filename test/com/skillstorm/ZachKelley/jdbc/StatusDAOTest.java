package com.skillstorm.ZachKelley.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.skillstorm.ZachKelley.Beans.Status;

public class StatusDAOTest {

static StatusDAO dao;
	
	@BeforeClass
	public static void setup() {
		try {
			dao = new StatusDAO();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void create() {
		try {
			Status status = dao.create(new Status("TEEESSSSSTTTTT"));
			System.out.println(status.getStatusId());
		}catch(SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void findAll() {
		try {
			Set<Status> statuses = dao.findAll();
			System.out.println(statuses.size());
			assertTrue(statuses.size() > 0);
		}catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	@Ignore
	public void delete() {
		try {
			boolean deleted = dao.delete(4);
			System.out.println(deleted);
			assertTrue(deleted);
		}catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void update() {
		Status status = new Status(4, "Test Status");
		try {
			boolean sucess = dao.Update(status);
			System.out.println(sucess);
			assertEquals(true, sucess);
		}catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}
}
