package com.example.homemedicalkit.dataBase

import androidx.room.Dao
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
    @Query("DELETE FROM medicines_table WHERE medicineId = :medicineId")
    suspend fun delete(medicineId: Int?)
    @Query("SELECT * FROM medicines_table WHERE medicineId = :medicineId")
    suspend fun getMedicine(medicineId: Int?): Medicine?
    @Query("SELECT * FROM medicines_table ORDER BY medicineId ASC")
    fun getAll(): Flow<List<Medicine>>
    @Query("SELECT * FROM medicines_table WHERE kit = :medicineKit ORDER BY medicineId ASC")
    fun getAllFromKit(medicineKit: Int): Flow<List<Medicine>>
}