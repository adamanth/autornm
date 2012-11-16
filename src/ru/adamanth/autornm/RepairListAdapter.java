package ru.adamanth.autornm;

import java.text.DateFormat;
import java.util.Date;

import ru.adamanth.autornm.db.tables.RepairTable;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.TextView;

public class RepairListAdapter extends ResourceCursorAdapter {

	private static DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);

	public RepairListAdapter(Context context, Cursor c) {
		super(context, R.layout.repair_list_item, c, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		if (cursor != null && cursor.getCount() > 0
				&& cursor.getPosition() >= 0) {
			TextView textView = (TextView) view.findViewById(R.id.text1);
			textView.setText(cursor.getString(cursor
					.getColumnIndex(RepairTable.COLUMN_NAME)));

			textView = (TextView) view.findViewById(R.id.text2);
			textView.setText(df.format(new Date(1000l * cursor.getLong(cursor
					.getColumnIndex(RepairTable.COLUMN_DATE)))));

			textView = (TextView) view.findViewById(R.id.text3);
			textView.setText(String.valueOf(cursor.getInt(cursor
					.getColumnIndex(RepairTable.COLUMN_MILEAGE))));

		}
	}

}
