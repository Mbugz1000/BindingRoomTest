package com.example.bindingroomtest;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

        //TODO Test this!!
        binding.setLifecycleOwner(this);
        binding.setViewModel(testViewModel);
//        testViewModel.getmTestEntity(0).observe(this, new Observer<TestEntity>() {
//            @Override
//            public void onChanged(TestEntity testEntity) {
//                testViewModel.setmTestEntity(testEntity);
//            }
//        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
