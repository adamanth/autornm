package ru.adamanth.autornm.model;

import java.util.Date;

public class Repair {
	
	private int id;
	private String name;
	private Date date;
	private int mileage;

	public Repair() {
	}

	public Repair(int id, String name, Date date, int mileage) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.mileage = mileage;
	}
	
	

	@Override
	public String toString() {
		return name;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

}
