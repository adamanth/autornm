package ru.adamanth.autornm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class RepairListActivity extends FragmentActivity
        implements RepairListFragment.Callbacks {

    private boolean mTwoPane;
    
    private RepairItemListFragment repairItemListFragment = null;

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
            arguments.putString(RepairItemListFragment.ARG_REPAIR_ID, id);
            
            if (repairItemListFragment == null) {
            	RepairItemListFragment repairItemListFragment = new RepairItemListFragment();
                repairItemListFragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.event_detail_container, repairItemListFragment)
                        .commit();
            } else {
            	repairItemListFragment.reload(arguments);
            }
            
            
        } else {
            Intent detailIntent = new Intent(this, EventDetailActivity.class);
            detailIntent.putExtra(RepairItemListFragment.ARG_REPAIR_ID, id);
            startActivity(detailIntent);
        }
    }
}
