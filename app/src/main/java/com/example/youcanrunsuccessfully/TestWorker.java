package com.example.youcanrunsuccessfully;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;

import java.util.List;

public class TestWorker extends Worker {

    public TestWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        List<TestBean> list = ((TestApplication) getApplicationContext()).getTestDataBase().testDao().queryAllItems();
        setOutputData(new Data.Builder().putString("data", new Gson().toJson(list)).build());
        return Result.SUCCESS;
    }
}
