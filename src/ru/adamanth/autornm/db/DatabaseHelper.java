package ru.adamanth.autornm.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import ru.adamanth.autornm.db.tables.RepairItemTable;
import ru.adamanth.autornm.db.tables.RepairTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 8;
	private static final String DATABASE_NAME = "autornm";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		RepairTable.onCreate(db);
		RepairItemTable.onCreate(db);
		
		fillDatabase(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		RepairTable.onUpgrade(db, oldVersion, newVersion);
		RepairItemTable.onUpgrade(db, oldVersion, newVersion);
		
		fillDatabase(db);
	}

	private void fillDatabase(SQLiteDatabase db) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
				Locale.GERMANY);

		ContentValues values = new ContentValues();
		values.put(RepairTable.COLUMN_NAME, "ТО-1");

		try {
			Date date = df.parse("20.10.2007");
			values.put(RepairTable.COLUMN_DATE, date.getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		values.put(RepairTable.COLUMN_MILEAGE, 14500);
		values.put(RepairTable.COLUMN_STATION_NAME, "СТС-МОТОРС");

		long repairId = db.insert(RepairTable.TABLE_REPAIR, "", values);

		ContentValues itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME, "Масло моторное");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 1400.00);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "GM");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "123456789");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 500);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Колодки тормозные передние");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 1900.34);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "Ate");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "2383201");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Колодки тормозные задние");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 956.93);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "TRW");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "GDB 1515");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		values.put(RepairTable.COLUMN_NAME,
				"ТО-2 AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

		try {
			Date date = df.parse("17.11.2008");
			values.put(RepairTable.COLUMN_DATE, date.getTime() / 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		values.put(RepairTable.COLUMN_MILEAGE, 29500);
		values.put(RepairTable.COLUMN_STATION_NAME, "Автомир AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

		repairId = db.insert(RepairTable.TABLE_REPAIR, "", values);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME, "Масло моторное");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 1700.00);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "GM");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "123456789");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 500);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Диск тормозной передний");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 3571.48);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "Ate");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "24.0125-0131.1");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Колодки тормозные передние");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 1546.18);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "Textar");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "2383201");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Диск тормозной задний");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 3571.48);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "TRW");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "DF 4051");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Колодки тормозные задние");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 956.93);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "TRW");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "GDB 1515");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);
	}
}
