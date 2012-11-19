package ru.adamanth.autornm;

import java.text.NumberFormat;

import ru.adamanth.autornm.db.tables.RepairItemTable;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.ResourceCursorAdapter;
import android.view.View;
import android.widget.TextView;

public class RepairItemListAdapter extends ResourceCursorAdapter {

	public RepairItemListAdapter(Context context, Cursor c) {
		super(context, R.layout.repair_item_list_item, c, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		if (cursor != null && cursor.getCount() > 0
				&& cursor.getPosition() >= 0) {
			TextView textView = (TextView) view.findViewById(R.id.spareName);
			textView.setText(cursor.getString(cursor
					.getColumnIndex(RepairItemTable.COLUMN_SPARE_NAME)));

			textView = (TextView) view.findViewById(R.id.sparePrice);
			textView.setText(NumberFormat
					.getCurrencyInstance()
					.format(cursor.getDouble(cursor
							.getColumnIndex(RepairItemTable.COLUMN_SPARE_PRICE))));

			textView = (TextView) view.findViewById(R.id.workCost);
			textView.setText(NumberFormat.getCurrencyInstance().format(
					cursor.getDouble(cursor
							.getColumnIndex(RepairItemTable.COLUMN_WORK_COST))));

		}
	}

}
