package com.example.bindingroomtest;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;
    private LiveData<TestEntity> mTestEntity = new MutableLiveData<>();
    private LiveData<List<TestEntity>> mListTestEntities = new MutableLiveData<>();
    private TestEntity testEntity = new TestEntity();
    private List<TestEntity> testEntityList = new ArrayList<>();

    private static final String TAG = "TestViewModel";

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        testEntity.setBanana("TestBanana");
        testEntity.setAnything("TestAnything");
    }

    public List<TestEntity> getTestEntityList() {
        return testEntityList;
    }

    public void setTestEntityList(List<TestEntity> testEntityList) {
        this.testEntityList = testEntityList;
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

    public LiveData<TestEntity> getmTestEntity(int id){
        mTestEntity = testRepository.getTestEntity(id);

        try {
            Log.i(TAG, "getmTestEntity: Gotten latest record from Room Database: " + mTestEntity.getValue().getId()  + ", " + id);
        }catch (NullPointerException e){
            Log.i(TAG, "getmTestEntity: Gotten latest record from Room Database, " + id);
        }

        return mTestEntity;
    }

}
