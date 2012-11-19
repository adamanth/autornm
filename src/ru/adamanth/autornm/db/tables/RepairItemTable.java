package ru.adamanth.autornm.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RepairItemTable {

	public static final String TABLE_REPAIR_ITEM = "repair_item";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_REPAIR_ID = "repair_id";
	public static final String COLUMN_SPARE_NAME = "spare_name";
	public static final String COLUMN_SPARE_PRICE = "spare_price";
	public static final String COLUMN_SPARE_MANUFACTURER = "spare_manufacturer";
	public static final String COLUMN_SPARE_CODE = "spare_code";
	public static final String COLUMN_WORK_COST = "work_cost";

	private static final String DATABASE_CREATE = "create table "
			+ TABLE_REPAIR_ITEM + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_REPAIR_ID
			+ " integer, " + COLUMN_SPARE_NAME + " text not null, "
			+ COLUMN_SPARE_PRICE + " real, " + COLUMN_SPARE_MANUFACTURER
			+ " text, " + COLUMN_SPARE_CODE + " text, " + COLUMN_WORK_COST
			+ " real" + ");";

	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion,
			int newVersion) {
		Log.w(RepairItemTable.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REPAIR_ITEM);
		onCreate(db);
	}

}
