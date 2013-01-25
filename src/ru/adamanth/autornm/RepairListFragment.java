package ru.adamanth.autornm;

import ru.adamanth.autornm.contentprovider.RepairContract;
import ru.adamanth.autornm.db.tables.RepairTable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ListView;

public class RepairListFragment extends ListFragment implements
		LoaderManager.LoaderCallbacks<Cursor> {

	private static final String TAG = "RepairListFragment";

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private static final int REPAIR_LIST_LOADER = 0x01;

	private RepairListCallbacks callbacks = dummyCallbacks;
	private int activatedPosition = ListView.INVALID_POSITION;

	private RepairListAdapter repairListAdapter;

	private boolean isLargeLayout;

	public interface RepairListCallbacks {

		public void onRepairSelected(String id);
	}

	private static RepairListCallbacks dummyCallbacks = new RepairListCallbacks() {
		@Override
		public void onRepairSelected(String id) {
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		isLargeLayout = getResources().getBoolean(R.bool.large_layout);

		setHasOptionsMenu(true);

		getLoaderManager().initLoader(REPAIR_LIST_LOADER, null, this);

		repairListAdapter = new RepairListAdapter(getActivity()
				.getBaseContext(), null);

		setListAdapter(repairListAdapter);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof RepairListCallbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		callbacks = (RepairListCallbacks) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		callbacks = dummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);

		activatedPosition = position;
		callbacks.onRepairSelected(String.valueOf(id));
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (activatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, activatedPosition);
		}
	}

	@SuppressLint("NewApi")
	public void setActivateOnItemClick(boolean activateOnItemClick) {
		final ListView listView = getListView();
		if (activateOnItemClick
				&& (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)) {

			listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

				@Override
				public void onItemCheckedStateChanged(ActionMode mode,
						int position, long id, boolean checked) {

					mode.getMenu().findItem(R.id.action_edit)
							.setVisible(listView.getCheckedItemCount() == 1);
				}

				@Override
				public boolean onActionItemClicked(ActionMode mode,
						MenuItem item) {
					// Respond to clicks on the actions in the CAB
					switch (item.getItemId()) {
					case R.id.action_edit:
						Log.i(TAG, "edit");
						mode.finish(); // Action picked, so close the CAB
						return true;
					case R.id.action_delete:
						Log.i(TAG, "delete");
						mode.finish(); // Action picked, so close the CAB
						return true;
					default:
						return false;
					}
				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					// Inflate the menu for the CAB
					MenuInflater inflater = mode.getMenuInflater();
					inflater.inflate(R.menu.repair_context, menu);
					return true;
				}

				@Override
				public void onDestroyActionMode(ActionMode mode) {
					// Here you can make any necessary updates to the activity
					// when
					// the CAB is removed. By default, selected items are
					// deselected/unchecked.
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					// Here you can perform updates to the CAB due to
					// an invalidate() request
					return false;
				}
			});
			/*
			 * listView.setOnLongClickListener(new View.OnLongClickListener() {
			 * 
			 * @Override public boolean onLongClick(View v) { Log.i(TAG,
			 * "onLongClick");
			 * listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			 * return true; } });
			 */
		} else {
			listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
		}
	}

	public void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(activatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		activatedPosition = position;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { RepairTable.COLUMN_ID, RepairTable.COLUMN_NAME,
				RepairTable.COLUMN_DATE, RepairTable.COLUMN_MILEAGE,
				RepairTable.COLUMN_STATION_NAME, RepairTable.SUBQUERY_COST };

		CursorLoader cursorLoader = new CursorLoader(getActivity(),
				RepairContract.Repair.CONTENT_URI, projection, null, null, null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		repairListAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		repairListAdapter.swapCursor(null);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.repair_action, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_new:
			/*
			 * DialogFragment dialogFragment = new RepairDialogFragment(); if
			 * (isLargeLayout) {
			 * 
			 * dialogFragment.show(getActivity().getSupportFragmentManager(),
			 * "dialog"); } else { FragmentTransaction transaction =
			 * getActivity() .getSupportFragmentManager().beginTransaction();
			 * transaction
			 * .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			 * transaction.add(android.R.id.content, dialogFragment)
			 * .addToBackStack(null).commit(); }
			 */

			Intent detailIntent = new Intent(this.getActivity(),
					RepairDetailActivity.class);
			startActivityForResult(detailIntent, 1);

			return true;
		case R.id.action_edit:
			Fragment dialogFragment1 = new RepairDialogFragment();
			if (isLargeLayout) {
				/*
				 * if (activatedPosition != ListView.INVALID_POSITION) { long id
				 * = getListView().getItemIdAtPosition( activatedPosition); Uri
				 * uri = RepairContract.buildUri(
				 * RepairContract.Repair.CONTENT_URI, id);
				 * 
				 * Bundle args = new Bundle();
				 * args.putParcelable(RepairContract.Repair.CONTENT_ITEM_TYPE,
				 * uri);
				 * 
				 * dialogFragment1.setArguments(args); }
				 * 
				 * dialogFragment1.show(getActivity().getSupportFragmentManager()
				 * , "dialog");
				 */

				Intent detailIntent1 = new Intent(this.getActivity(),
						RepairDetailActivity.class);

				if (activatedPosition != ListView.INVALID_POSITION) {
					long id = getListView().getItemIdAtPosition(
							activatedPosition);
					Uri uri = RepairContract.buildUri(
							RepairContract.Repair.CONTENT_URI, id);

					Bundle args = new Bundle();
					detailIntent1.putExtra(
							RepairContract.Repair.CONTENT_ITEM_TYPE, uri);

					dialogFragment1.setArguments(args);
				}

				startActivityForResult(detailIntent1, 2);

			} else {
				FragmentTransaction transaction = getActivity()
						.getSupportFragmentManager().beginTransaction();
				transaction
						.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				transaction.add(android.R.id.content, dialogFragment1)
						.addToBackStack(null).commit();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		Log.i(TAG,
				"onActivityResult, requestCode = "
						+ String.valueOf(requestCode) + ", resultCode = "
						+ resultCode);
	}

}
