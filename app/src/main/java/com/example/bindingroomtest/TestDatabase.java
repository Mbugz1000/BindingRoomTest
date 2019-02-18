package com.example.bindingroomtest;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TestEntity.class}, version = 1, exportSchema = false)
public abstract class TestDatabase extends RoomDatabase {
    private static TestDatabase INSTANCE;
    public abstract TestDao testDao();

    private static final String TAG = "Test_Database";

    public static TestDatabase getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (TestDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TestDatabase.class, TAG).build();
                Log.i(TAG, "getInstance: Database Built!!" + INSTANCE.toString());
            }
        }
        return INSTANCE;
    }
}
