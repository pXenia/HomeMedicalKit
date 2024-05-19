package com.example.homemedicalkit.featureMedicine.domain.repository

import com.example.homemedicalkit.featureMedicine.domain.model.Medicine
import kotlinx.coroutines.flow.Flow


interface MedicineRepository {

    suspend fun insert(medicine: Medicine)

    suspend fun update(medicine: Medicine)

    suspend fun delete(medicineId: Int?)

    suspend fun getMedicine(medicineId: Int?): Medicine?

    fun getAll(): Flow<List<Medicine>>
    fun getAllFromKit(kit: Int): Flow<List<Medicine>>
}