package com.example.homemedicalkit.dataBase

import kotlinx.coroutines.flow.Flow


interface MedicineRepository {

    suspend fun insert(medicine: Medicine)

    suspend fun update(medicine: Medicine)

    suspend fun delete(medicine: Medicine)

    suspend fun getMedicine(medicineId: Int?): Medicine?

    fun getAll(): Flow<List<Medicine>>
    fun getAllFromKit(kit: Int): Flow<List<Medicine>>
}