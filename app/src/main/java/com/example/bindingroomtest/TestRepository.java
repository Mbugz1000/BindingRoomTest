package com.example.bindingroomtest;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestRepository {
    private LiveData<TestEntity> mTestEntity = new MutableLiveData<>();
    private LiveData<List<TestEntity>> mListTestEntities = new MutableLiveData<>();
    private TestDao testDao;

    private static final String TAG = "Test Repository";

    public TestRepository(Application application) {
        TestDatabase testDatabase = TestDatabase.getInstance(application);
        testDao = testDatabase.testDao();
    }

    public void insert(TestEntity testEntity){
        new insertAsyncTask(testDao).execute(testEntity);
    }

    private static class insertAsyncTask extends AsyncTask<TestEntity, Void, Void> {
        private TestDao testDao;

        insertAsyncTask(TestDao dao) {
            testDao = dao;
        }

        @Override
        protected Void doInBackground(TestEntity... testEntities) {
            if (testEntities[0] != null){
                long ID = testDao.insert(testEntities[0]);
                Log.i(TAG, "doInBackground: Insertion Successful!!" +
                        testEntities[0].getAnything()+ ", " + testEntities[0].getBanana() + ", " + ID);
            }
            return null;
        }
    }

    public LiveData<TestEntity> getTestEntity(int id){
        mTestEntity = testDao.getTestEntity(id);
        return mTestEntity;
    }

    public LiveData<List<TestEntity>> getTestEntities(){
        mListTestEntities = testDao.getTestEntities();
        Log.i(TAG, "getmTestEntities: Getting list of entities from Room Database: " + mListTestEntities.getValue());
        return mListTestEntities;
    }

}
