package com.example.homemedicalkit.feature_medicine.data.dataSourse

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.homemedicalkit.feature_medicine.domain.model.Kit
import kotlinx.coroutines.flow.Flow
@Dao
interface KitDao {
    @Upsert
    suspend fun insert(kit: Kit)
    @Query("DELETE FROM kits_table WHERE kitId = :kitId")
    suspend fun delete(kitId: Int?)
    @Query("SELECT * FROM kits_table WHERE kitId = :kitId")
    suspend fun getKit(kitId: Int?): Kit?
    @Query("SELECT * FROM kits_table ORDER BY kitId ASC")
    fun getAll(): Flow<List<Kit>>
}