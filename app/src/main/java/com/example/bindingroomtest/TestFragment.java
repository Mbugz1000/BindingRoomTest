package com.example.bindingroomtest;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bindingroomtest.databinding.TestFragmentBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TestFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.test_fragment,container,false);
        final TestViewModel testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(testViewModel);

        // RecycleView Adapter
        TestRecycleViewAdapter recycleViewAdapter = new TestRecycleViewAdapter(getContext());
        binding.recyclerView.setAdapter(recycleViewAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Observer not necessary for Recycler View
        //testViewModel.testEntities.observe(this, recycleViewAdapter::setList);

        //Setting individual views using observer
        testViewModel.testEntity.observe(this,testEntity -> {
            binding.anythingText.setText(testEntity.getAnything());
            binding.bananaText.setText(testEntity.getBanana());
        } );

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}