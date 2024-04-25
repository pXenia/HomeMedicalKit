package com.example.homemedicalkit.dataBase

import kotlinx.coroutines.flow.Flow


interface KitRepository {

    suspend fun insert(kit: Kit)

    suspend fun delete(kit: Kit)

    fun getAll(): Flow<List<Kit>>
    suspend fun getKit(kitId: Int?): Kit?
}