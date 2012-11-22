package ru.adamanth.autornm;

import ru.adamanth.autornm.contentprovider.RepairContract;
import ru.adamanth.autornm.db.tables.RepairItemTable;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

public class RepairItemListFragment extends ListFragment implements
		LoaderManager.LoaderCallbacks<Cursor> {

	public static final String ARG_REPAIR_ID = "repair_id";

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private static final int REPAIR_ITEM_LIST_LOADER = 0x01;

	// private RepairListCallbacks mCallbacks = sDummyCallbacks;
	private int mActivatedPosition = ListView.INVALID_POSITION;

	private RepairItemListAdapter repairItemListAdapter;

	/*
	 * public interface RepairListCallbacks {
	 * 
	 * public void onItemSelected(String id); }
	 * 
	 * private static RepairListCallbacks sDummyCallbacks = new RepairListCallbacks() {
	 * 
	 * @Override public void onItemSelected(String id) { } };
	 */

	public RepairItemListFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		setEmptyText("Empty");

		setListShown(false);

		repairItemListAdapter = new RepairItemListAdapter(getActivity()
				.getBaseContext(), null);
		setListAdapter(repairItemListAdapter);

		getLoaderManager().initLoader(REPAIR_ITEM_LIST_LOADER, getArguments(),
				this);
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
		/*
		 * if (!(activity instanceof RepairListCallbacks)) { throw new
		 * IllegalStateException(
		 * "Activity must implement fragment's callbacks."); }
		 * 
		 * mCallbacks = (RepairListCallbacks) activity;
		 */
	}

	@Override
	public void onDetach() {
		super.onDetach();
		// mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position,
			long id) {
		super.onListItemClick(listView, view, position, id);
		// mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	public void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { RepairItemTable.COLUMN_ID,
				RepairItemTable.COLUMN_REPAIR_ID,
				RepairItemTable.COLUMN_SPARE_NAME,
				RepairItemTable.COLUMN_SPARE_PRICE,
				RepairItemTable.COLUMN_SPARE_MANUFACTURER,
				RepairItemTable.COLUMN_SPARE_CODE,
				RepairItemTable.COLUMN_WORK_COST };

		String repairId = String.valueOf(0l);
		if (args != null && args.containsKey(ARG_REPAIR_ID)) {
			repairId = getArguments().getString(ARG_REPAIR_ID);
		}

		CursorLoader cursorLoader = new CursorLoader(
				getActivity(),
				Uri.withAppendedPath(
						RepairContract.RepairItem.CONTENT_REPAIR_URI, repairId),
				projection, null, null, null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		repairItemListAdapter.swapCursor(cursor);

		if (isResumed()) {
			setListShown(true);
		} else {
			setListShownNoAnimation(true);
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		repairItemListAdapter.swapCursor(null);
	}

	public void reload(Bundle args) {
		getLoaderManager().restartLoader(REPAIR_ITEM_LIST_LOADER, args, this);
	}
}
