package ru.adamanth.autornm.model;

import java.util.Date;

import ru.adamanth.autornm.db.tables.RepairTable;
import android.database.Cursor;

public class Repair {

	private long id;
	private String name;
	private Date date;
	private long mileage;
	private String stationName;
	private double cost;

	public Repair() {
	}

	public Repair(long id, String name, Date date, long mileage,
			String stationName, double cost) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.mileage = mileage;
		this.stationName = stationName;
		this.cost = cost;
	}

	public Repair(Cursor cursor) {

		int index = cursor.getColumnIndex(RepairTable.COLUMN_ID);
		if (index >= 0) {
			id = cursor.getLong(index);
		}

		index = cursor.getColumnIndex(RepairTable.COLUMN_NAME);
		if (index >= 0) {
			name = cursor.getString(index);
		}

		index = cursor.getColumnIndex(RepairTable.COLUMN_MILEAGE);
		if (index >= 0) {
			mileage = cursor.getLong(index);
		}

		index = cursor.getColumnIndex(RepairTable.COLUMN_DATE);
		if (index >= 0) {
			date = new Date(1000l * cursor.getLong(index));
		}

		index = cursor.getColumnIndex(RepairTable.COLUMN_STATION_NAME);
		if (index >= 0) {
			stationName = cursor.getString(index);
		}

		index = cursor.getColumnIndex(RepairTable.COLUMN_COST);
		if (index >= 0) {
			cost = cursor.getDouble(index);
		}
	}

	@Override
	public String toString() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getMileage() {
		return mileage;
	}

	public void setMileage(long mileage) {
		this.mileage = mileage;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

}
