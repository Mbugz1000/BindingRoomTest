package com.example.bindingroomtest;

import android.app.Application;
import android.util.Log;

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
    private static int idCount = 0;

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    private static final String TAG = "TestViewModel";


    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        testEntity.setBanana("TestBanana");
        testEntity.setAnything("TestAnything");
    }

    public void saveTestEntity() {
        testEntity.setId(idCount);
        testRepository.insert(testEntity);
        Log.i(TAG, "saveTestEntity: Setter Called from ViewModel!!" +
                testEntity.getAnything() + ", " + testEntity.getBanana() + ", " + idCount);
        idCount += 1;
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
            Log.i(TAG, "getmTestEntity: Gotten first record from Room Database: " + mTestEntity.getValue().getAnything());
        }catch (NullPointerException e){
            Log.i(TAG, "getmTestEntity: Gotten first record from Room Database");
        }

        return mTestEntity;
    }
}
