package com.example.bindingroomtest;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;
    private LiveData<List<TestEntity>> mListTestEntities = new MutableLiveData<>();
    public MutableLiveData<TestEntity> mTestEntity;
    private TestEntity testEntity;
    private int recordID;

    private static final String TAG = "TestViewModel";

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        testEntity = new TestEntity();
        mTestEntity = new MutableLiveData<>();
    }
    public int getRecordID() {
        Log.i(TAG, "getRecordID: Got by Two way databinding");
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
        Log.i(TAG, "setRecordID: Set by Two way data binding " + recordID);
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }


    public void saveTestEntity() {
        testRepository.insert(testEntity);
    }

    public LiveData<List<TestEntity>> getmTestEntities() {
        mListTestEntities = testRepository.getTestEntities();

        try {
            Log.i(TAG, "getmTestEntities: Getting list of entities from Room Database: " + mListTestEntities.getValue());
        }catch (NullPointerException e){
            Log.i(TAG, "getmTestEntities: Getting list of entities from Room Database");
        }
        return mListTestEntities;
    }

    public void loadEntity(){
        Log.i(TAG, "loadEntity: record ID Value: " + recordID);
        if (recordID == 0){ recordID +=  1; }
        mTestEntity.setValue(testRepository.loadEntity(recordID));
    }



}
