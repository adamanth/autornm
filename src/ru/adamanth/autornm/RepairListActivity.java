package ru.adamanth.autornm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RepairListActivity extends FragmentActivity implements
		RepairListFragment.Callbacks {

	private boolean isLargeLayout;

	private RepairItemListFragment repairItemListFragment = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_list);

		isLargeLayout = getResources().getBoolean(R.bool.large_layout);

		if (isLargeLayout) {
			((RepairListFragment) getSupportFragmentManager().findFragmentById(
					R.id.repair_list)).setActivateOnItemClick(true);
		}
	}

	@Override
	public void onItemSelected(String id) {
		if (isLargeLayout) {
			Bundle arguments = new Bundle();
			arguments.putString(RepairItemListFragment.ARG_REPAIR_ID, id);

			if (repairItemListFragment == null) {
				RepairItemListFragment repairItemListFragment = new RepairItemListFragment();
				repairItemListFragment.setArguments(arguments);
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.event_detail_container,
								repairItemListFragment).commit();
			} else {
				repairItemListFragment.reload(arguments);
			}

		} else {
			Intent detailIntent = new Intent(this, RepairItemListActivity.class);
			detailIntent.putExtra(RepairItemListFragment.ARG_REPAIR_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.repair_action, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_new:
			DialogFragment dialogFragment = new RepairDialogFragment();
			if (isLargeLayout) {

				dialogFragment.show(getSupportFragmentManager(), "dialog");
			} else {
				FragmentTransaction transaction = getSupportFragmentManager()
						.beginTransaction();
				transaction
						.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				transaction.add(android.R.id.content, dialogFragment)
						.addToBackStack(null).commit();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
