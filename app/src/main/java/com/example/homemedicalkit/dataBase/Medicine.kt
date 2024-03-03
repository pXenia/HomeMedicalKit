package com.example.homemedicalkit.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines_table")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    var medicineId: Long = 0L,
    @ColumnInfo(name = "name")
    var medicineName: String = "",
    @ColumnInfo(name = "image")
    var medicineImage: String = "",
    @ColumnInfo(name = "description")
    var medicineDescription: String = "")
