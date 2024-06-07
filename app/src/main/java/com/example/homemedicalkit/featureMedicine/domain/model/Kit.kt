package com.example.homemedicalkit.featureMedicine.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kits_table")
data class Kit(
    @PrimaryKey(autoGenerate = true)
    var kitId: Int? = null,
    @ColumnInfo(name = "kit_name")
    var kitName: String,
    @ColumnInfo(name = "color")
    var kitColor: Int
)
