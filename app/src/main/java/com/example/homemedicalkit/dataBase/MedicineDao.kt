package com.example.homemedicalkit.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Insert
    suspend fun insert(medicine: Medicine)
    @Update
    suspend fun update(medicine: Medicine)
    @Delete
    suspend fun delete(medicine: Medicine)
    @Query("SELECT * FROM medicines_table WHERE medicineId = :medicineId")
    fun getMedicine(medicineId: Long): Medicine?
    @Query("SELECT * FROM medicines_table ORDER BY medicineId ASC")
    fun getAll(): Flow<List<Medicine>>
}