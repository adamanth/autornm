package ru.adamanth.autornm.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RepairTable {

	public static final String TABLE_REPAIR = "repair";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_MILEAGE = "mileage";

	private static final String DATABASE_CREATE = "create table "
			+ TABLE_REPAIR + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null, " + COLUMN_DATE + " integer," + COLUMN_MILEAGE
			+ " integer" + ");";

	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		Log.w(RepairTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPAIR);
		onCreate(db);
	}

}
