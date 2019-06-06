package com.example.youcanrunsuccessfully;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TestDao {

    @Query("select * from TestBean")
    public List<TestBean> queryAllItems();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertItems(TestBean... testBeans);
}
