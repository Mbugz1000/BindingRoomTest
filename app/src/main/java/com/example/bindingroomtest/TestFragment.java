package com.example.bindingroomtest;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
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

        final String TAG = "TestFragment";

        //TODO Test this!!
        binding.setLifecycleOwner(this);
        binding.setViewModel(testViewModel);

        final Observer<TestEntity> entityObserver = testEntity -> {
            Log.i(TAG, "onCreateView: Observer for loadEntity Called!!! " + testEntity.getAnything() + ", " + testEntity.getBanana() + ", " + testEntity.getId() );
            binding.anythingText.setText(testEntity.getAnything());
            binding.bananaText.setText(testEntity.getBanana());

        };

        //Calls this fucntion on it's own when data changes. No need to use a button to get the data
        testViewModel.mTestEntity.observe(this, entityObserver);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
