package com.example.homemedicalkit.feature_medicine.domain.repository

import com.example.homemedicalkit.feature_medicine.domain.model.Kit
import kotlinx.coroutines.flow.Flow


interface KitRepository {

    suspend fun insert(kit: Kit)

    suspend fun delete(kitId: Int?)

    fun getAll(): Flow<List<Kit>>
    suspend fun getKit(kitId: Int?): Kit?
}