package com.example.bindingroomtest;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestRepository {
    private TestEntity testEntity;
    private LiveData<List<TestEntity>> mListTestEntities = new MutableLiveData<>();
    private TestDao testDao;
    private static long ID;

    private static final String TAG = "Test Repository";

    public TestRepository(Application application) {
        TestDatabase testDatabase = TestDatabase.getInstance(application);
        testDao = testDatabase.testDao();
        testEntity = new TestEntity();
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    public TestEntity getTestEntity() {
        return testEntity;
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
                ID = testDao.insert(testEntities[0]);
                Log.i(TAG, "doInBackground: Insertion Successful!!" +
                        testEntities[0].getAnything()+ ", " + testEntities[0].getBanana() + ", " + ID);
            }
            return null;
        }
    }

    public TestEntity loadEntity(int id){
        testEntity = testDao.getTestEntity(id);
        Log.i(TAG, "loadEntity: Gotten last record from Room Database, " + id + ", " + testEntity.getAnything() + ", " + testEntity.getBanana());
        return testEntity;
    }


    public LiveData<List<TestEntity>> getTestEntities(){
        mListTestEntities = testDao.getTestEntities();
        Log.i(TAG, "getmTestEntities: Getting list of entities from Room Database");
        return mListTestEntities;
    }

}
