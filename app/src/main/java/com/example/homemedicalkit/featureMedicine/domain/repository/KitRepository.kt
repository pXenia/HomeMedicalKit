package com.example.homemedicalkit.featureMedicine.domain.repository

import com.example.homemedicalkit.featureMedicine.domain.model.Kit
import kotlinx.coroutines.flow.Flow


interface KitRepository {

    suspend fun insert(kit: Kit)

    suspend fun delete(kitId: Int?)

    fun getAll(): Flow<List<Kit>>
    suspend fun getKit(kitId: Int?): Kit?
}