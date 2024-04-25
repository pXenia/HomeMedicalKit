package com.example.homemedicalkit.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Medicine::class, Kit::class],version = 1, exportSchema = false)
abstract class MedicalKitDatabase: RoomDatabase() {
    abstract val medicineDao: MedicineDao
    abstract val kitDao: KitDao
    companion object{
        const val DATABASE_NAME = "medicine_db"
    }

}