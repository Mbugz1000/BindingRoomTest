package com.example.bindingroomtest;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;

import java.lang.reflect.Array;
import java.util.List;

public class SpinnerExtentions{

    public static void setSpinnerEntries(Spinner spinner, List<Integer> spinnerEntries){
        if (spinnerEntries != null){
            ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, spinnerEntries);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }
    }

}
