package com.example.homemedicalkit.feature_medicine.data.repository

import com.example.homemedicalkit.feature_medicine.domain.model.Kit
import com.example.homemedicalkit.feature_medicine.data.dataSourse.KitDao
import com.example.homemedicalkit.feature_medicine.domain.repository.KitRepository
import kotlinx.coroutines.flow.Flow

class KitRepositoryImpl(
    private val dao: KitDao
): KitRepository {

    override suspend fun insert(kit: Kit) {
        return dao.insert(kit)
    }

    override suspend fun delete(kitId: Int?) {
        return dao.delete(kitId)
    }

    override fun getAll(): Flow<List<Kit>> {
        return dao.getAll()
    }

    override suspend fun getKit(kitId: Int?): Kit? {
        return dao.getKit(kitId)
    }
}