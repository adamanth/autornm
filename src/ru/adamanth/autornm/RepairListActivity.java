package ru.adamanth.autornm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;

public class RepairListActivity extends FragmentActivity implements
		RepairListFragment.RepairListCallbacks,
		RepairItemListFragment.RepairItemListCallbacks {

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
	public void onRepairSelected(String id) {
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
				// repairItemListFragment.setActivateOnItemClick(true);
			} else {
				repairItemListFragment.reload(arguments);
			}

		} else {
			Intent detailIntent = new Intent(this, RepairItemListActivity.class);
			detailIntent.putExtra(RepairItemListFragment.ARG_REPAIR_ID, id);
			startActivity(detailIntent);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onRepairItemSelected(String id, String repairId) {
		if (isLargeLayout) {
			RepairListFragment repairListFragment = (RepairListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.repair_list);

			if (repairListFragment.isVisible()) {
				getSupportFragmentManager().beginTransaction()
						.hide(repairListFragment).commit();

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					getActionBar().setDisplayHomeAsUpEnabled(true);
				}

				findViewById(R.id.repair_item_container).setVisibility(
						View.VISIBLE);

				Bundle arguments = new Bundle();
				arguments.putString(RepairItemDetailFragment.ARG_ITEM_ID, id);

				RepairItemDetailFragment fragment = new RepairItemDetailFragment();
				fragment.setArguments(arguments);
				
				getSupportFragmentManager().beginTransaction()
						.hide(repairListFragment)
						.replace(R.id.repair_item_container, fragment).commit();
			}

			/*
			 * Intent detailIntent = new Intent(this,
			 * RepairItemDetailActivity.class);
			 * detailIntent.putExtra(RepairItemListFragment.ARG_REPAIR_ID,
			 * repairId); startActivity(detailIntent);
			 */
		} else {

		}
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {

			RepairListFragment repairListFragment = (RepairListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.repair_list);

			if (!repairListFragment.isVisible()) {
				getSupportFragmentManager().beginTransaction()
						.show(repairListFragment).commit();

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					getActionBar().setDisplayHomeAsUpEnabled(false);
				}
			}

			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
