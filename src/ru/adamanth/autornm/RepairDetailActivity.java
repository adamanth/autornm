package ru.adamanth.autornm;

import ru.adamanth.autornm.contentprovider.RepairContract;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class RepairDetailActivity extends FragmentActivity {

	protected static final String TAG = "RepairDetailActivity";

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showAsPopup(this);

		setContentView(R.layout.activity_repair_detail);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		if (savedInstanceState == null) {
			RepairDialogFragment fragment = new RepairDialogFragment();

			Uri uri = getIntent().getParcelableExtra(
					RepairContract.Repair.CONTENT_ITEM_TYPE);
			if (uri != null) {
				Bundle arguments = new Bundle();

				arguments.putParcelable(
						RepairContract.Repair.CONTENT_ITEM_TYPE, uri);
				fragment.setArguments(arguments);
			}

			getSupportFragmentManager().beginTransaction()
					.add(R.id.repair_detail_container, fragment).commit();
		}
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case android.R.id.home:
			NavUtils.navigateUpTo(this, new Intent(this,
					RepairListActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showAsPopup(Activity activity) {
		activity.requestWindowFeature(Window.FEATURE_ACTION_BAR);
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND,
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		LayoutParams params = activity.getWindow().getAttributes();
		params.height = LayoutParams.MATCH_PARENT;
		params.width = 850; // fixed width
		params.alpha = 1.0f;
		params.dimAmount = 0.5f;
		activity.getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		Log.i(TAG, "onPause");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		Log.i(TAG, "onSaveInstanceState");
	}

}
