package com.example.bindingroomtest;

import java.util.List;

public interface BindableAdapter<T> {
    //This allowsus to use the Binding Adapter with the specific Recycler Adapters we want

    void setList(T List);
}
