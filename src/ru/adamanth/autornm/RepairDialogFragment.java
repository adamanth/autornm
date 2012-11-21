package ru.adamanth.autornm;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class RepairDialogFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	private Date date;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		date = new Date();
		
		View v = inflater.inflate(R.layout.fragment_repair_detail, container,
				false);

		EditText dateText = (EditText) v.findViewById(R.id.dateText);
		dateText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDatePickerDialog(v);
			}
		});

		return v;
	}

	public void showDatePickerDialog(View v) {
		Bundle args = new Bundle();
		args.putLong(DatePickerFragment.ARG_DATE, date.getTime());
		
		DatePickerFragment datePickerFragment = new DatePickerFragment();
		datePickerFragment.setOnDateSetListener(this);

		datePickerFragment.setArguments(args);
		
		datePickerFragment.show(getActivity().getSupportFragmentManager(),
				"datePicker");
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);

		date = c.getTime();

		TextView tv = (TextView) getView().findViewById(R.id.dateText);
		tv.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
	}

	public static class DatePickerFragment extends DialogFragment {
		
		public static final String ARG_DATE = "date";

		private DatePickerDialog.OnDateSetListener onDateSetListener = null;

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Calendar c = Calendar.getInstance();
			
			if (getArguments() != null && getArguments().containsKey(ARG_DATE)) {
				c.setTime(new Date(getArguments().getLong(ARG_DATE)));
			}
			
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), onDateSetListener, year,
					month, day);
		}

		public void setOnDateSetListener(
				DatePickerDialog.OnDateSetListener onDateSetListener) {
			this.onDateSetListener = onDateSetListener;
		}
		
		

	}
}
