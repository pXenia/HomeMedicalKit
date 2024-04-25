package com.example.homemedicalkit.dataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface KitDao {
    @Upsert
    suspend fun insert(kit: Kit)
    @Delete
    suspend fun delete(kit: Kit)
    @Query("SELECT * FROM kits_table WHERE kitId = :kitId")
    suspend fun getKit(kitId: Int?): Kit?
    @Query("SELECT * FROM kits_table ORDER BY kitId ASC")
    fun getAll(): Flow<List<Kit>>
}