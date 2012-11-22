package ru.adamanth.autornm.contentprovider;

import android.content.ContentResolver;
import android.net.Uri;

public final class RepairContract {

	public static final String AUTHORITY = "ru.adamanth.autornm.contentprovider";
	public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
	
	public static Uri buildUri(Uri uri, long id) {
		return buildUri(uri, String.valueOf(id));
	}

	public static Uri buildUri(Uri uri, String id) {
		return Uri.withAppendedPath(uri, id);
	}

	public static class Repair {

		public static final String BASE_PATH = "repair";

		public static final Uri CONTENT_URI = Uri.withAppendedPath(
				AUTHORITY_URI, BASE_PATH);

		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
				+ "/" + BASE_PATH;

		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
				+ "/" + BASE_PATH;
	}

	public static class RepairItem {

		public static final String BASE_PATH = "repair_item";

		public static final Uri CONTENT_URI = Uri.withAppendedPath(
				AUTHORITY_URI, BASE_PATH);

		public static final Uri CONTENT_REPAIR_URI = Uri.withAppendedPath(
				CONTENT_URI, "repair_id");

		public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
				+ "/" + BASE_PATH;

		public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
				+ "/" + BASE_PATH;
	}

}
