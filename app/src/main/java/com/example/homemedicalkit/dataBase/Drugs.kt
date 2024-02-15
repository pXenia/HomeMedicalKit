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
    @ColumnInfo(name = "expiration_date")
    var drugExpirationDate: Date = Date(1/1/2020),
    @ColumnInfo(name = "image")
    var drugImage: String = "",
    @ColumnInfo(name = "description")
    var drugDescription: String = "")
