package com.example.bindingroomtest;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TestDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(TestEntity testEntity);

    @Delete
    void delete(TestEntity testEntity);

    @Update
    void update(TestEntity testEntity);

    @Query("SELECT * from TestTable ORDER BY Anything ASC")
    LiveData<List<TestEntity>> getTestEntities();

    //No need to use Live Data for single row that's being called
    @Query("SELECT * from TestTable where id =:id")
    TestEntity getTestEntity(int id);
}
