package ru.adamanth.autornm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RepairItemDetailFragment extends Fragment {

	public static final String ARG_ITEM_ID = "repair_item_id";

	private String repairItemId = "0";

	public RepairItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments() != null
				&& getArguments().containsKey(
						RepairItemDetailFragment.ARG_ITEM_ID)) {

			repairItemId = getArguments().getString(
					RepairItemDetailFragment.ARG_ITEM_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_repair_item_detail,
				container, false);

		// Show the dummy content as text in a TextView.

		((TextView) rootView.findViewById(R.id.repair_item_detail)).setText(repairItemId);

		return rootView;
	}
}
