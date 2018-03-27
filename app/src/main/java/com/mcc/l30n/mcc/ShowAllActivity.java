package com.mcc.l30n.mcc;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;

import com.mcc.l30n.mcc.adapter.AllProfileRecyclerAdapter;
import com.mcc.l30n.mcc.databinding.ActivityShowAllBinding;
import com.mcc.l30n.mcc.utils.DatabaseHandler;

public class ShowAllActivity extends AppCompatActivity {
    private ActivityShowAllBinding binding;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_all);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseHandler = new DatabaseHandler(this);

        binding.rvProfiles.setLayoutManager(new LinearLayoutManager(this));
        binding.srlProfiles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.srlProfiles.setRefreshing(true);
                getAllProfile();
            }
        });
        binding.srlProfiles.setRefreshing(true);
        getAllProfile();
    }

    public void getAllProfile() {
        binding.rvProfiles.setAdapter(new AllProfileRecyclerAdapter(ShowAllActivity.this, databaseHandler.getAllProfile()));
        binding.srlProfiles.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
