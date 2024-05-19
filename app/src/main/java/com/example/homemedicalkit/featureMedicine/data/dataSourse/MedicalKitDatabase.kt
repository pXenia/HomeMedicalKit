package com.example.homemedicalkit.featureMedicine.data.dataSourse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homemedicalkit.featureMedicine.domain.model.Kit
import com.example.homemedicalkit.featureMedicine.domain.model.Medicine

@Database(entities = [Medicine::class, Kit::class],version = 1, exportSchema = false)
abstract class MedicalKitDatabase: RoomDatabase() {
    abstract val medicineDao: MedicineDao
    abstract val kitDao: KitDao
    companion object{
        const val DATABASE_NAME = "medicine_db"
    }

}