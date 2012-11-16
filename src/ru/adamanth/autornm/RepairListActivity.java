package ru.adamanth.autornm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class RepairListActivity extends FragmentActivity
        implements RepairListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_list);
        
        if (findViewById(R.id.event_detail_container) != null) {
            mTwoPane = true;
            ((RepairListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.repair_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(EventDetailFragment.ARG_ITEM_ID, id);
            EventDetailFragment fragment = new EventDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.event_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, EventDetailActivity.class);
            detailIntent.putExtra(EventDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
