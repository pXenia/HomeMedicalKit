package com.example.homemedicalkit.dataBase

import kotlinx.coroutines.flow.Flow

class MedicineRepositoryImpl(
    private val dao: MedicineDao
): MedicineRepository {
    override suspend fun insert(medicine: Medicine) {
        return dao.insert(medicine)
    }

    override suspend fun update(medicine: Medicine) {
        return dao.update(medicine)
    }

    override suspend fun delete(medicine: Medicine) {
        return dao.delete(medicine)
    }

    override fun getMedicine(medicineId: Long): Medicine? {
        return dao.getMedicine(medicineId)
    }

    override fun getAll(): Flow<List<Medicine>> {
        return dao.getAll()
    }

}