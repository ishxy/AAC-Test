package com.example.youcanrunsuccessfully;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.youcanrunsuccessfully.databinding.MainActivityBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
//        final List<TestBean> data = ((TestApplication)getApplication()).getTestDataBase().testDao().queryAllItems();
        WorkRequest request = new OneTimeWorkRequest.Builder(TestWorker.class).build();
        WorkManager.getInstance().enqueue(request);
        WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(@Nullable WorkInfo workInfo) {
                if (workInfo != null) {
                    if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                        final List<TestBean> data = new Gson().fromJson(workInfo.getOutputData().getString("data"),
                                new TypeToken<List<TestBean>>() {
                                }.getType());
                        final TestAdapter testAdapter = new TestAdapter(data);
                        mainActivityBinding.recyclerView.setAdapter(testAdapter);
                        mainViewModel.times.observe(MainActivity.this, new Observer<Integer>() {
                            @Override
                            public void onChanged(@Nullable Integer integer) {
                                if (integer % 10 == 0) {
                                    Collections.reverse(data);
                                    testAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
