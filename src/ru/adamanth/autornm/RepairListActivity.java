package ru.adamanth.autornm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class RepairListActivity extends FragmentActivity implements
		RepairListFragment.RepairListCallbacks,
		RepairItemListFragment.RepairItemListCallbacks {

	private boolean isLargeLayout;

	private boolean isDetailMode = false;

	private RepairItemListFragment repairItemListFragment = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_list);

		isLargeLayout = getResources().getBoolean(R.bool.large_layout);

		if (isLargeLayout) {
			((RepairListFragment) getSupportFragmentManager().findFragmentById(
					R.id.repair_list)).setActivateOnItemClick(true);
			((RepairItemListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.repair_item_list))
					.setActivateOnItemClick(true);
			
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.hide(getSupportFragmentManager()
					.findFragmentById(R.id.repair_item_detail));
			ft.commit();
		}
	}

	@Override
	public void onRepairSelected(String id) {
		if (isLargeLayout) {
			Bundle arguments = new Bundle();
			arguments.putString(RepairItemListFragment.ARG_REPAIR_ID, id);

			if (repairItemListFragment == null) {
				repairItemListFragment = (RepairItemListFragment) getSupportFragmentManager()
						.findFragmentById(R.id.repair_item_list);
				/*
				 * RepairItemListFragment repairItemListFragment = new
				 * RepairItemListFragment();
				 * repairItemListFragment.setArguments(arguments);
				 * getSupportFragmentManager() .beginTransaction()
				 * .replace(R.id.event_detail_container,
				 * repairItemListFragment).commit();
				 */
			} /* else { */
			repairItemListFragment.reload(arguments);
			// }

		} else {
			Intent detailIntent = new Intent(this, RepairItemListActivity.class);
			detailIntent.putExtra(RepairItemListFragment.ARG_REPAIR_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public void onRepairItemSelected(String id) {

		if (!isDetailMode) {
			
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.hide(getSupportFragmentManager()
					.findFragmentById(R.id.repair_list));
			ft.show(getSupportFragmentManager()
					.findFragmentById(R.id.repair_item_detail));
			ft.commit();


			/*
			Fragment f = getSupportFragmentManager().findFragmentById(
					R.id.repair_list);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
					LayoutParams.MATCH_PARENT, 0.0f);
			f.getView().setLayoutParams(params);

			f = getSupportFragmentManager().findFragmentById(
					R.id.repair_item_list);

			params = new LinearLayout.LayoutParams(0,
					LayoutParams.MATCH_PARENT, 1.0f);
			f.getView().setLayoutParams(params);

			FrameLayout fl = (FrameLayout) findViewById(R.id.repair_item_container);

			params = new LinearLayout.LayoutParams(0,
					LayoutParams.MATCH_PARENT, 3.0f);
			fl.setLayoutParams(params);
			*/

			isDetailMode = true;
		} else {

		}

		/*
		 * Intent detailIntent = new Intent(this,
		 * RepairItemListDetailActivity.class);
		 * detailIntent.putExtra(RepairItemListFragment.ARG_REPAIR_ID, id);
		 * startActivity(detailIntent);
		 */
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
				&& isDetailMode) {
			
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.hide(getSupportFragmentManager()
					.findFragmentById(R.id.repair_item_detail));
			ft.show(getSupportFragmentManager()
					.findFragmentById(R.id.repair_list));
			ft.commit();

			
			/*
			Fragment f = getSupportFragmentManager().findFragmentById(
					R.id.repair_list);

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
					LayoutParams.MATCH_PARENT, 1.0f);
			f.getView().setLayoutParams(params);

			f = getSupportFragmentManager().findFragmentById(
					R.id.repair_item_list);

			params = new LinearLayout.LayoutParams(0,
					LayoutParams.MATCH_PARENT, 3.0f);
			f.getView().setLayoutParams(params);

			FrameLayout fl = (FrameLayout) findViewById(R.id.repair_item_container);

			params = new LinearLayout.LayoutParams(0,
					LayoutParams.MATCH_PARENT, 0.0f);
			fl.setLayoutParams(params);
			*/

			isDetailMode = false;

			
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}
}
