package ru.adamanth.autornm;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class RepairItemListDetailActivity extends FragmentActivity implements
		RepairItemListFragment.RepairItemListCallbacks {

	private boolean isLargeLayout;

	private RepairItemListFragment repairItemListFragment = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_item_list_detail);

		isLargeLayout = getResources().getBoolean(R.bool.large_layout);

		/*
		 * if (isLargeLayout) { ((RepairListFragment)
		 * getSupportFragmentManager().findFragmentById(
		 * R.id.repair_list)).setActivateOnItemClick(true); }
		 */
	}

	@Override
	public void onRepairItemSelected(String id) {

		/*
		if (isLargeLayout) {
			Bundle arguments = new Bundle();
			arguments.putString(RepairItemListFragment.ARG_REPAIR_ID, id);

			if (repairItemListFragment == null) {
				repairItemListFragment = (RepairItemListFragment) getSupportFragmentManager()
						.findFragmentById(R.id.repair_item_list);
			}
			repairItemListFragment.reload(arguments);
		} else {
			Intent detailIntent = new Intent(this, RepairItemListActivity.class);
			detailIntent.putExtra(RepairItemListFragment.ARG_REPAIR_ID, id);
			startActivity(detailIntent);
		}
		*/
	}
}
