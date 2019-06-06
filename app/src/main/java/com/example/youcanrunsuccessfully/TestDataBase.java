package com.example.youcanrunsuccessfully;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {TestBean.class}, version = 1, exportSchema = false)
public abstract class TestDataBase extends RoomDatabase {

    public abstract TestDao testDao();
}
