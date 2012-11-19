package ru.adamanth.autornm.contentprovider;

import ru.adamanth.autornm.db.DatabaseHelper;
import ru.adamanth.autornm.db.tables.RepairItemTable;
import ru.adamanth.autornm.db.tables.RepairTable;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class RepairContentProvider extends ContentProvider {

	private DatabaseHelper databaseHelper;

	private static final int REPAIRS = 10;
	private static final int REPAIR_ID = 20;
	private static final int REPAIR_ITEMS = 30;

	private static final String AUTHORITY = "ru.adamanth.autornm.contentprovider";

	private static final String BASE_PATH = "repairs";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);

	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/repairs";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/repair";

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		sURIMatcher.addURI(AUTHORITY, BASE_PATH, REPAIRS);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", REPAIR_ID);
		sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/items/#", REPAIR_ITEMS);
	}

	@Override
	public boolean onCreate() {
		databaseHelper = new DatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		// Check if the caller has requested a column which does not exists
		// checkColumns(projection);


		int uriType = sURIMatcher.match(uri);
		switch (uriType) {
		case REPAIRS:
			queryBuilder.setTables(RepairTable.TABLE_REPAIR);
			break;
		case REPAIR_ID:
			queryBuilder.setTables(RepairTable.TABLE_REPAIR);
			queryBuilder.appendWhere(RepairTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;
		case REPAIR_ITEMS:
			queryBuilder.setTables(RepairItemTable.TABLE_REPAIR_ITEM);
			queryBuilder.appendWhere(RepairItemTable.COLUMN_REPAIR_ID + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
