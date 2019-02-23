package com.example.bindingroomtest;

import android.util.Log;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BindingAdapters {

    private static final String TAG = "TestBindingAdapter";

    @BindingAdapter("android:setList")
    public static <T> void setList(RecyclerView recyclerView, T entityList){
        if (recyclerView.getAdapter() instanceof BindableAdapter){
            Log.i(TAG, "setList: Binding adapter called");
            ((BindableAdapter) recyclerView.getAdapter()).setList(entityList);
        }
    }
}
