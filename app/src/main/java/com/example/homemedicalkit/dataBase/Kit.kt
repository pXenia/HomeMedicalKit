package com.example.homemedicalkit.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kits_table")
data class Kit(
    @PrimaryKey(autoGenerate = true)
    var kitId: Int? = null,
    @ColumnInfo(name = "name")
    var kitName: String = "",
    @ColumnInfo(name = "color")
    var kitColor: Int = -1,
)
