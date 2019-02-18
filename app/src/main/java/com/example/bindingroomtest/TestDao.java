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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(TestEntity testEntity);

    @Delete
    void delete(TestEntity testEntity);

    @Update
    void update(TestEntity testEntity);

    @Query("SELECT * from TestTable ORDER BY Anything ASC")
    LiveData<List<TestEntity>> getTestEntities();

    @Query("SELECT * from TestTable where id =:id")
    LiveData<TestEntity> getTestEntity(int id);
}
