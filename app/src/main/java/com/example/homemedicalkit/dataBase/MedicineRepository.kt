package com.example.homemedicalkit.dataBase

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


interface MedicineRepository {

    suspend fun insert(medicine: Medicine)

    suspend fun update(medicine: Medicine)

    suspend fun delete(medicine: Medicine)

    fun getMedicine(medicineId: Long): Flow<Medicine>

    fun getAll(): Flow<List<Medicine>>
}