package com.example.homemedicalkit.feature_medicine.domain.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines_table")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    var medicineId: Int? = null,
    @ColumnInfo(name = "name")
    var medicineName: String,
    @ColumnInfo(name = "date")
    var medicineDate: Long,
    @ColumnInfo(name = "kit")
    var medicineKit: Int,
    @ColumnInfo(name = "few")
    var medicineNumberFew: Boolean,
    @ColumnInfo(name = "image")
    var medicineImage: String,
    @ColumnInfo(name = "description")
    var medicineDescription: String)

class InvalidMedicineException(message: String): Exception(message)