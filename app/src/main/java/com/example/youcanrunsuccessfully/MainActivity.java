package com.example.youcanrunsuccessfully;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.youcanrunsuccessfully.databinding.MainActivityBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TestViewModel mainViewModel;
    private MainActivityBinding mainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mainActivityBinding.setMainViewModel(mainViewModel);
        mainActivityBinding.setLifecycleOwner(this);
        mainActivityBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final List<TestBean> data = ((TestApplication)getApplication()).getTestDataBase().testDao().queryAllItems();
        final TestAdapter testAdapter = new TestAdapter(data);
        mainActivityBinding.recyclerView.setAdapter(testAdapter);
        mainViewModel.times.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer%10 == 0) {
                    Collections.reverse(data);
                    testAdapter.notifyDataSetChanged();
                }
            }
        });
    }


}
