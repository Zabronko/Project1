package com.skillstorm.ZachKelley.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.skillstorm.ZachKelley.Beans.Status;

public class StatusDAO {
private Connection connection;
	
	public StatusDAO() throws SQLException {
		super();
		String url = "jdbc:mysql://localhost:3306/expensereimbersement";
		String username = "root";
		String password = "root";
		this.connection = DriverManager.getConnection(url,username,password);
	}
	
	
	public Status create(Status status) throws SQLException {
		String sql = "insert into status(status) values(?);";
		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, status.getStatus());
		
		statement.executeUpdate();
		ResultSet rs = statement.getGeneratedKeys();
		rs.next();
		status.setStatusId(rs.getInt(1));
		
		return status;
	}
	
	public Set<Status> findAll() throws SQLException {
		String sql = "select * from status;";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		Set<Status> Statuses = new HashSet<Status>();
		while(rs.next()) {
			Status row = new Status(rs.getInt("status_id"),
					rs.getString("status"));
			Statuses.add(row);
		}
		return Statuses;
	}
	
	public boolean delete(int id) throws SQLException {
		String sql = "delete from status where status_id=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		
		return statement.executeUpdate() > 0;
	}
	
	public boolean Update(Status status) throws SQLException {
		String sql = "Update Status set status = ? where Status_id=?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(2, status.getStatusId());
		statement.setString(1, status.getStatus());
		
		return statement.executeUpdate() > 0;
	}
}
