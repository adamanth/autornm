package ru.adamanth.autornm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class RepairItemDetailActivity extends FragmentActivity implements
		RepairItemListFragment.RepairItemListCallbacks {

	private boolean isLargeLayout;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_item_twopane);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		isLargeLayout = getResources().getBoolean(R.bool.large_layout);

		RepairItemListFragment repairItemListFragment = (RepairItemListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.repair_item_list);

		if (isLargeLayout) {
			repairItemListFragment.setActivateOnItemClick(true);
		}

		/*
		 * if (savedInstanceState == null) { Bundle arguments = new Bundle();
		 * arguments.putString( RepairItemListFragment.ARG_REPAIR_ID,
		 * getIntent().getStringExtra( RepairItemListFragment.ARG_REPAIR_ID));
		 * repairItemListFragment.setArguments(arguments); }
		 */
	}

	@Override
	public void onRepairItemSelected(String id, String repairId) {
		if (isLargeLayout) {
			Bundle arguments = new Bundle();
			arguments.putString(RepairItemDetailFragment.ARG_ITEM_ID, id);

			RepairItemDetailFragment fragment = new RepairItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.repair_item_container, fragment).commit();

		} else {
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			NavUtils.navigateUpTo(this, new Intent(this,
					RepairListActivity.class));
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
