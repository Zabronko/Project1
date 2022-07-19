package com.skillstorm.ZachKelley.Beans;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name="Status")
public class Status {

	@Id
	@Column(name="status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int statusId;
	
	@Column(name="status")
	private String status;
	
	public Status(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	
	public Status(String status) {
		super();
		this.status = status;
	}
	
	public Status() {
		super();
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "{statusId:" + statusId + ", status:" + status + "}";
	}

	
	
}
