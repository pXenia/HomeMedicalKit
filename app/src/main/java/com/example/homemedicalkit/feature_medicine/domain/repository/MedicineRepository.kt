package com.example.homemedicalkit.feature_medicine.domain.repository

import com.example.homemedicalkit.feature_medicine.domain.model.Medicine
import kotlinx.coroutines.flow.Flow


interface MedicineRepository {

    suspend fun insert(medicine: Medicine)

    suspend fun update(medicine: Medicine)

    suspend fun delete(medicineId: Int?)

    suspend fun getMedicine(medicineId: Int?): Medicine?

    fun getAll(): Flow<List<Medicine>>
    fun getAllFromKit(kit: Int): Flow<List<Medicine>>
}