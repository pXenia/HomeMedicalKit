package com.example.homemedicalkit.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DrugDao {
    @Insert
    suspend fun insert(drag: Drugs)
    @Update
    suspend fun update(task: Drugs)
    @Delete
    suspend fun delete(task: Drugs)
    @Query("SELECT * FROM drugs_table WHERE drugId = :drugId")
    fun getDrug(drugId: Long): LiveData<Drugs>
    @Query("SELECT * FROM drugs_table ORDER BY drugId ASC")
    fun getAll(): LiveData<List<Drugs>>
}