package com.shxy.paging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;


public class MyViewModel extends ViewModel {

    private LiveData<PagedList<DataHolder>> dataSource = new LivePagedListBuilder(new MyDataSource.Factory(),
            new PagedList.Config.Builder().setInitialLoadSizeHint(20).setPageSize(20).build()).setInitialLoadKey(0).build();
    ;

    public LiveData<PagedList<DataHolder>> getDataSource() {
        return dataSource;
    }

    public void setDataSource(LiveData<PagedList<DataHolder>> dataSource) {
        this.dataSource = dataSource;
    }
}
