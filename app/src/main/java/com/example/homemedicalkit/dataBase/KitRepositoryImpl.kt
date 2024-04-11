package com.example.homemedicalkit.dataBase

import kotlinx.coroutines.flow.Flow

class KitRepositoryImpl(
    private val dao: KitDao
): KitRepository {

    override suspend fun insert(kit: Kit) {
        return dao.insert(kit)
    }

    override suspend fun delete(kit: Kit) {
        return dao.delete(kit)
    }

    override fun getAll(): Flow<List<Kit>> {
        return dao.getAll()
    }

}