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

    override suspend fun delete(medicineId: Int?){
        return dao.delete(medicineId)
    }

    override suspend fun getMedicine(medicineId: Int?): Medicine? {
        return dao.getMedicine(medicineId)
    }

    override fun getAll(): Flow<List<Medicine>> {
        return dao.getAll()
    }
    override fun getAllFromKit(kit: Int): Flow<List<Medicine>> {
        return dao.getAllFromKit(kit)
    }

}