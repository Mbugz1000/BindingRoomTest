package com.example.bindingroomtest;

import androidx.databinding.InverseBindingListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.view.View;
import android.widget.SpinnerAdapter;

import java.util.List;


public class SpinnerExtentions{

    //Using Generic method
    public static <T> void setSpinnerEntries(Spinner spinner, List<T> spinnerEntries){
        if (spinnerEntries != null){
            ArrayAdapter<T> arrayAdapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, spinnerEntries);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }
    }

//------------------------------------------ Not Necessary ---------------------------------------------------------------------

    //Setting Spinner on item selected listener
    public static void setSpinnerItemSelectedListener(Spinner spinner, ItemSelectedListener listener){
        if (listener == null){
            spinner.setOnItemSelectedListener(null);
        }else{
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (view.getTag() != ((Integer) position)) {
                        listener.onItemSelected(parent.getItemAtPosition(position));
                    }
                }

                @Override
                public void onNothingSelected (AdapterView < ? > parent){
                    // Nothing
                }
            });

        }
    }

    public static void setSpinnerInverseBindingListener(Spinner spinner, InverseBindingListener listener){
        if (listener == null){
            spinner.setOnItemSelectedListener(null);
        }else{
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (view.getTag() != ((Integer) position)){
                        listener.onChange();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Nothing
                }
            });
        }
    }

    //Set Spinner Value
    public static <T> void setSpinnerValue(Spinner spinner, T value ){
        SpinnerAdapter adapter = spinner.getAdapter();
        if (adapter != null){
            int position = ((ArrayAdapter) adapter).getPosition(value);
            spinner.setSelection(position, false);
            spinner.setTag(position);
        }
    }

    // Getting Spinner Value
    public static Object getSpinnerValue(Spinner spinner){
        return spinner.getSelectedItem();
    }

    public interface ItemSelectedListener{
        <T> void onItemSelected(T item);
    }

}
