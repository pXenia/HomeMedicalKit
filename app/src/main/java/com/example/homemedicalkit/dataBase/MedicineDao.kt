package com.example.homemedicalkit.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Upsert
    suspend fun insert(medicine: Medicine)
    @Update
    suspend fun update(medicine: Medicine)
    @Delete
    suspend fun delete(medicine: Medicine)
    @Query("SELECT * FROM medicines_table WHERE medicineId = :medicineId")
    suspend fun getMedicine(medicineId: Int?): Medicine?
    @Query("SELECT * FROM medicines_table ORDER BY medicineId ASC")
    fun getAll(): Flow<List<Medicine>>
}