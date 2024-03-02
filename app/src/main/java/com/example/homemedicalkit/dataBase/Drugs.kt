package com.example.homemedicalkit.dataBase

import android.accounts.AuthenticatorDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "drugs_table")
data class Drugs(
    @PrimaryKey(autoGenerate = true)
    var drugId: Long = 0L,
    @ColumnInfo(name = "name")
    var drugName: String = "",
    @ColumnInfo(name = "image")
    var drugImage: String = "",
    @ColumnInfo(name = "description")
    var drugDescription: String = "")
