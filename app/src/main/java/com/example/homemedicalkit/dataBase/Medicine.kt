package com.example.homemedicalkit.dataBase

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.homemedicalkit.ui.theme.LightBlue1
import com.example.homemedicalkit.ui.theme.Red80
import com.example.homemedicalkit.ui.theme.Yellow80

@Entity(tableName = "medicines_table")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    var medicineId: Long? = 0L,
    @ColumnInfo(name = "name")
    var medicineName: String = "",
    @ColumnInfo(name = "date")
    var medicineDate: String = "",
    @ColumnInfo(name = "kit")
    var medicineKit: Long = 0,
    @ColumnInfo(name = "few")
    var medicineNumberFew: Boolean = false,
    @ColumnInfo(name = "image")
    var medicineImage: String = "",
    @ColumnInfo(name = "description")
    var medicineDescription: String = "")

class InvalidMedicineException(message: String): Exception(message)