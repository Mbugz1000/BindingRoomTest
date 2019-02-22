package com.example.bindingroomtest;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TestTable")
public class TestEntity{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Anything")
    private String anything;
    private String banana;

    private static final String TAG = "TestEntity";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        //Does nothing
    }

    public String getAnything() {
        Log.i(TAG, "getAnything: Got by Two way databinding");
        return anything;
    }

    public void setAnything(String anything) {
        this.anything = anything;
        Log.i(TAG, "setAnything: Set by Two way data binding, " + anything);
    }

    public String getBanana() {
        Log.i(TAG, "getBanana: Got by Two way databinding ");
        return banana;
    }

    public void setBanana(String banana) {
        Log.i(TAG, "setBanana: Set by Two way data binding, " + banana);
        this.banana = banana;
    }
}
