package com.example.youcanrunsuccessfully;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

public class TestViewModel extends ViewModel {

    MutableLiveData<Integer> times = new MutableLiveData<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            times.setValue(times.getValue() + 1);
            handler.sendEmptyMessageDelayed(0, 1000L);
        }
    };

    public TestViewModel() {
        times.setValue(0);
        handler.sendEmptyMessage(0);
    }

    public MutableLiveData<Integer> getTimes() {
        return times;
    }
}
