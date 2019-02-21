package com.example.bindingroomtest;

import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.List;

import androidx.databinding.BindingAdapter;

public class SpinnerBindings {
    @BindingAdapter("entries")
    public static void setEntries(Spinner spinner, List<Integer> spinnerEntries){
        SpinnerExtentions.setSpinnerEntries(spinner, spinnerEntries);
    }

}
