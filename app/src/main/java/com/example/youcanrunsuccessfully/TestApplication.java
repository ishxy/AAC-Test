package com.example.youcanrunsuccessfully;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE;

public class TestApplication extends Application {

    private TestDataBase testDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        testDataBase = Room.databaseBuilder(getApplicationContext(),TestDataBase.class,"test1.db")
                .allowMainThreadQueries()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        List<TestBean> dataSource = getDataSource();
                        db.beginTransaction();
                        try {
                            for (TestBean testBean : dataSource) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("url", testBean.getUrl());
                                contentValues.put("text", testBean.getText());
                                db.insert("testBean", SQLiteDatabase.CONFLICT_REPLACE, contentValues);
                            }
                            db.setTransactionSuccessful();
                        } finally {
                            db.endTransaction();
                        }
                    }
                })
                .build();
    }

    public TestDataBase getTestDataBase() {
        return testDataBase;
    }

    private List<TestBean> getDataSource() {
        List<TestBean> data = new ArrayList<>();
        String[] textArray = getResources().getStringArray(R.array.data_text);
        String[] urlArray = getResources().getStringArray(R.array.data_url);
        for (int i = 0; i < textArray.length; i++) {
            data.add(new TestBean(urlArray[i],textArray[i]));
        }
        return data;
    }
}
