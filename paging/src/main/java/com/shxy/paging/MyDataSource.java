package com.shxy.paging;

import android.arch.paging.DataSource;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDataSource extends ItemKeyedDataSource<Integer, DataHolder> {

    private static final String TAG = "MyDataSource";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<DataHolder> callback) {
        Log.i(TAG, "loadInitial");
        callback.onResult(loadData(params.requestedInitialKey == null ? 0 : params.requestedInitialKey, params.requestedLoadSize));
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataHolder> callback) {
        Log.i(TAG, "loadAfter");
        callback.onResult(loadData(params.key, params.requestedLoadSize));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataHolder> callback) {
        Log.i(TAG, "loadBefore");
    }


    @NonNull
    @Override
    public Integer getKey(@NonNull DataHolder item) {
        Log.i(TAG, "getKey");
        return item.getId();
    }

    private List<DataHolder> loadData(int start, int loadSize) {
        List<DataHolder> res = new ArrayList<>(loadSize);
        for (int i = 1; i <= loadSize; i++) {
            String data = "this is no " + (start + i) + " item";
            DataHolder dataHolder = new DataHolder((start + i), data);
            res.add(dataHolder);
        }
        return res;
    }

    static class Factory extends DataSource.Factory<Integer, DataHolder> {

        @Override
        public DataSource<Integer, DataHolder> create() {
            return new MyDataSource();
        }
    }
}
