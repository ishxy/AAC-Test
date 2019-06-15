package com.shxy.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.shxy.paging.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);
        final MyPagedAdapter myPagedAdapter = new MyPagedAdapter();
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        activityMainBinding.recyclerView.setAdapter(myPagedAdapter);
        myViewModel.getDataSource().observe(this, new Observer<PagedList<DataHolder>>() {
            @Override
            public void onChanged(@Nullable PagedList<DataHolder> dataHolders) {
                myPagedAdapter.submitList(dataHolders);
            }
        });

    }
}
