package ru.adamanth.autornm.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import ru.adamanth.autornm.db.tables.RepairTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper {
	
	private final DatabaseOpenHelper databaseOpenHelper;

	public DatabaseHelper(Context context) {
		databaseOpenHelper = new DatabaseOpenHelper(context);
	}

	public SQLiteDatabase getDb(){
		return databaseOpenHelper.getWritableDatabase();
	}

	private static class DatabaseOpenHelper extends SQLiteOpenHelper {

		private static final int DATABASE_VERSION = 3;
		private static final String DATABASE_NAME = "autornm";

		public DatabaseOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			RepairTable.onCreate(db);

			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
					Locale.GERMANY);

			ContentValues values = new ContentValues();
			values.put(RepairTable.COLUMN_NAME, "рн-1");

			try {
				Date date = df.parse("20.10.2007");
				values.put(RepairTable.COLUMN_DATE, date.getTime() / 1000);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// values.put(REPAIR_TABLE_COL_DATE, 1419120000);
			values.put(RepairTable.COLUMN_MILEAGE, 14500);

			db.insert(RepairTable.TABLE_REPAIR, "", values);

			values.put(RepairTable.COLUMN_NAME,
					"рн-2 AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

			try {
				Date date = df.parse("17.11.2008");
				values.put(RepairTable.COLUMN_DATE, date.getTime() / 1000);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// values.put(REPAIR_TABLE_COL_DATE, 1450656000);
			values.put(RepairTable.COLUMN_MILEAGE, 29500);

			db.insert(RepairTable.TABLE_REPAIR, "", values);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			RepairTable.onUpgrade(db, oldVersion, newVersion);
		}
	}

}
