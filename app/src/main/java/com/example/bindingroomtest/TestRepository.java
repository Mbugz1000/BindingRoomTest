package com.example.bindingroomtest;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestRepository {
    private TestEntity testEntity = new TestEntity();
    private LiveData<List<TestEntity>> mListTestEntities = new MutableLiveData<>();
    private TestDao testDao;
    private static long ID;

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
                ID = testDao.insert(testEntities[0]);
                Log.i(TAG, "doInBackground: Insertion Successful!!" +
                        testEntities[0].getAnything()+ ", " + testEntities[0].getBanana() + ", " + ID);
            }
            return null;
        }
    }

    public TestEntity loadEntity(int id){
//        testEntity = testDao.getTestEntity(id);
//        Log.i(TAG, "loadEntity: Gotten last record from Room Database, " + id);
//        return testEntity;
        new loadEntityAsyncTask(new loadEntityAsyncTask.AsyncResponse(){
            @Override
            public void processFinish(TestEntity output) {
              testEntity = output;
            }
        }).execute(id)
    }

    public static class loadEntityAsyncTask extends AsyncTask<Integer, Void, TestEntity>{
        private TestDao testDao;

        public interface AsyncResponse {
            void processFinish(TestEntity output);
        }

        public AsyncResponse delegate = null;

        loadEntityAsyncTask(TestDao dao) {
            testDao = dao;
        }

        @Override
        protected TestEntity doInBackground(Integer... integers) {
            return testDao.getTestEntity(((int) integers[0]));
        }

        @Override
        protected void onPostExecute(TestEntity result) {
            delegate.processFinish(result);
        }

    }

    public LiveData<List<TestEntity>> getTestEntities(){
        mListTestEntities = testDao.getTestEntities();
        Log.i(TAG, "getmTestEntities: Getting list of entities from Room Database");
        return mListTestEntities;
    }

}
