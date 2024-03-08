package com.example.homemedicalkit.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Medicine::class],version = 1, exportSchema = false)
abstract class MedicalKitDatabase: RoomDatabase() {
    abstract val medicineDao: MedicineDao
    companion object{
        const val DATABASE_NAME = "medicine_db"
    }

}