package ru.adamanth.autornm;

import java.text.DateFormat;
import java.util.Calendar;

import ru.adamanth.autornm.contentprovider.RepairContract;
import ru.adamanth.autornm.db.tables.RepairTable;
import ru.adamanth.autornm.model.Repair;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RepairDialogFragment extends Fragment {

	private static DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

	private Calendar date;

	private EditText nameText;

	private TextView dateText;

	private EditText mileageText;

	private EditText stationText;

	private Uri repairUri;

	private static final String TAG = "RepairDialogFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);

		date = Calendar.getInstance();

		// Check from the saved Instance
		repairUri = (savedInstanceState == null) ? null
				: (Uri) savedInstanceState
						.getParcelable(RepairContract.Repair.CONTENT_ITEM_TYPE);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_repair_detail, container,
				false);

		nameText = (EditText) v.findViewById(R.id.nameText);
		mileageText = (EditText) v.findViewById(R.id.mileageText);
		stationText = (EditText) v.findViewById(R.id.stationText);

		dateText = (TextView) v.findViewById(R.id.dateText);
		dateText.setText(df.format(date.getTime()));
		dateText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDatePickerDialog(v);
			}
		});

		// Or passed from the other activity
		Bundle args = getArguments();
		if (args != null) {
			repairUri = args
					.getParcelable(RepairContract.Repair.CONTENT_ITEM_TYPE);

			fillData(repairUri);
		}

		return v;
	}

	@SuppressLint("NewApi")
	private void showDatePickerDialog(View v) {
		DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
				new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker arg0, int y, int m, int d) {
						date.set(y, m, d);
						dateText.setText(df.format(date.getTime()));
					}
				}, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date
						.get(Calendar.DAY_OF_MONTH));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			datePickerDialog.getDatePicker().setCalendarViewShown(false);
		}

		datePickerDialog.show();
	}

	private void fillData(Uri uri) {
		String[] projection = { RepairTable.COLUMN_ID, RepairTable.COLUMN_NAME,
				RepairTable.COLUMN_DATE, RepairTable.COLUMN_MILEAGE,
				RepairTable.COLUMN_STATION_NAME };

		Cursor cursor = getActivity().getContentResolver().query(uri,
				projection, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
			Repair repair = new Repair(cursor);

			nameText.setText(repair.getName());
			dateText.setText(df.format(repair.getDate()));
			mileageText.setText(String.valueOf(repair.getMileage()));
			stationText.setText(repair.getStationName());

			date.setTime(repair.getDate());
		}
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.dialog_action, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_cancel:
			Log.i(TAG, "action_cancel");
			getActivity().setResult(Activity.RESULT_CANCELED);
			getActivity().finish();
			return true;
		case R.id.action_save:
			Log.i(TAG, "action_save");
			save();
			getActivity().setResult(Activity.RESULT_OK);
			getActivity().finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void save() {
		Log.i(TAG, "save");

		String name = nameText.getText().toString();
		long mileage = Long.valueOf(mileageText.getText().toString());
		String station = stationText.getText().toString();
		long dateLong = date.getTime().getTime() / 1000l;

		ContentValues values = new ContentValues();
		values.put(RepairTable.COLUMN_NAME, name);
		values.put(RepairTable.COLUMN_DATE, dateLong);
		values.put(RepairTable.COLUMN_MILEAGE, mileage);
		values.put(RepairTable.COLUMN_STATION_NAME, station);

		if (repairUri == null) {
			repairUri = getActivity().getContentResolver().insert(
					RepairContract.Repair.CONTENT_URI, values);
		} else {
			getActivity().getContentResolver().update(repairUri, values, null,
					null);
		}
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		Log.i(TAG, "onPause");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.i(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
		outState.putParcelable(RepairContract.Repair.CONTENT_ITEM_TYPE, repairUri);
	}
}
