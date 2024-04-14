package com.example.homemedicalkit.dataBase


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines_table")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    var medicineId: Int? = null,
    @ColumnInfo(name = "name")
    var medicineName: String = "",
    @ColumnInfo(name = "date")
    var medicineDate: Long = 0L,
    @ColumnInfo(name = "kit")
    var medicineKit: Long = 0L,
    @ColumnInfo(name = "few")
    var medicineNumberFew: Boolean = false,
    @ColumnInfo(name = "image")
    var medicineImage: String = "",
    @ColumnInfo(name = "description")
    var medicineDescription: String = "")

class InvalidMedicineException(message: String): Exception(message)