package com.myclass.entity;

import java.util.Date;

public class Job {
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String string) {
		this.startDate = string;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String string) {
		this.endDate = string;
	}
	public Job(int id, String name, String startDate, String endDate) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Job() {
		super();
	}
	
	
}
