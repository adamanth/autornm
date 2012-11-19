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

	private static final int DATABASE_VERSION = 6;
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
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 1600.00);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "Ate");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "456123789");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Колодки тормозные задние");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 950.00);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "TRW");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "789123654");
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
				"Колодки тормозные передние");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 1350.00);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "Textar");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "456123789");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);

		itemValues = new ContentValues();
		itemValues.put(RepairItemTable.COLUMN_REPAIR_ID, repairId);
		itemValues.put(RepairItemTable.COLUMN_SPARE_NAME,
				"Колодки тормозные задние");
		itemValues.put(RepairItemTable.COLUMN_SPARE_PRICE, 1020.00);
		itemValues.put(RepairItemTable.COLUMN_SPARE_MANUFACTURER, "TRW");
		itemValues.put(RepairItemTable.COLUMN_SPARE_CODE, "789123654");
		itemValues.put(RepairItemTable.COLUMN_WORK_COST, 600);
		db.insert(RepairItemTable.TABLE_REPAIR_ITEM, "", itemValues);
	}
}
