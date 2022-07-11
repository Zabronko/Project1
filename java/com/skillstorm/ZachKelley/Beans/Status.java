package com.skillstorm.ZachKelley.Beans;

import java.util.Objects;

public class Status {

	private int statusId;
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
	public int hashCode() {
		return Objects.hash(status, statusId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		return Objects.equals(status, other.status) && statusId == other.statusId;
	}
	
	
}
