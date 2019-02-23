package com.example.bindingroomtest;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;
    public MutableLiveData<TestEntity> testEntity;
    private int recordID;
    private MutableLiveData<List<Integer>> numberList;
    public LiveData<List<TestEntity>> testEntities;
    private static int count;

    private static final String TAG = "TestViewModel";

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        testEntity = new MutableLiveData<>();
        count = 69;
        testEntities = testRepository.getTestEntities();
        // Create list of integers Java 8
        numberList = new MutableLiveData<>(IntStream.rangeClosed(0, count)
                .boxed().collect(Collectors.toList()));
    }
    public int getRecordID() {
        Log.i(TAG, "getRecordID: Got by Two way databinding");
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
        Log.i(TAG, "setRecordID: Set by Two way data binding " + recordID);
    }

    public MutableLiveData<List<Integer>> getNumberList() {
        return numberList;
    }

    public MutableLiveData<TestEntity> getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity.setValue(testEntity);
    }

    public void addNumber(){
        count +=1;
        numberList.setValue(IntStream.rangeClosed(1, count)
                .boxed().collect(Collectors.toList()));
    }

    public void setNumberList(List<Integer> numberList) {
        this.numberList.setValue(numberList);
    }

    public void saveTestEntity(TestEntity testEntity) {
        testRepository.insert(testEntity);
    }

    public LiveData<List<TestEntity>> getTestEntities() {
        Log.i(TAG, "getmListTestEntities: Getting list of entities from Room Database: No Error");
        return testEntities;
    }

    public void loadEntity(){
        Log.i(TAG, "loadEntity: record ID Value: " + recordID);
        if (recordID == 0){ recordID +=  1; }
        new loadEntityAsyncTask(testRepository, TestViewModel.this).execute(recordID);
    }

    public static class loadEntityAsyncTask extends AsyncTask<Integer, Void, TestEntity> {
        private TestRepository testRepository;
        private TestViewModel viewModelReference;

        loadEntityAsyncTask(TestRepository repository, TestViewModel viewModel) {
            testRepository = repository;
            viewModelReference = viewModel;
        }

        @Override
        protected TestEntity doInBackground(Integer... integers) {
            return testRepository.loadEntity(((int) integers[0]));
        }

        @Override
        protected void onPostExecute(TestEntity result) {
            // Using setValue coz onPost Execute is called from the Main Thread
            viewModelReference.testEntity.setValue(result);
            Log.i(TAG, "AsyncTaskloadEntity: Gotten last record from Room Database AsyncTask, " + result.getId() + ", " + result.getAnything() + ", " + result.getBanana());
        }

    }

}
