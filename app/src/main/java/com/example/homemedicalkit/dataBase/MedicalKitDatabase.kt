package com.example.homemedicalkit.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Medicine::class],version = 1, exportSchema = false)
abstract class MedicalKitDatabase: RoomDatabase() {
    abstract val medicineDao: MedicineDao
    companion object{
        @Volatile
        private var INSTANCE: MedicalKitDatabase? = null
        // возвращает экземпляр БД
        fun getInstance(context: Context): MedicalKitDatabase{
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MedicalKitDatabase::class.java,
                    "medicalKit_database"
                ).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}