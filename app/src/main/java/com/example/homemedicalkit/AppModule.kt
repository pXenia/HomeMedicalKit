package com.example.homemedicalkit

import android.app.Application
import androidx.room.Room
import com.example.homemedicalkit.dataBase.MedicalKitDatabase
import com.example.homemedicalkit.dataBase.MedicineRepository
import com.example.homemedicalkit.dataBase.MedicineRepositoryImpl
import com.example.homemedicalkit.dataBase.useCase.DeleteMedicineUseCase
import com.example.homemedicalkit.dataBase.useCase.GetMedicineUseCase
import com.example.homemedicalkit.dataBase.useCase.MedicineUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMedicalKitDatabase(app:Application): MedicalKitDatabase{
       return  Room.databaseBuilder(
           app,
           MedicalKitDatabase::class.java,
           MedicalKitDatabase.DATABASE_NAME
           ).build()
    }
    @Provides
    @Singleton
    fun provideMedicineRepository(db:MedicalKitDatabase): MedicineRepository{
        return MedicineRepositoryImpl(db.medicineDao)
    }
    @Provides
    fun provideMedicineUseCases(repository: MedicineRepository):MedicineUseCases{
        return MedicineUseCases(
            getMedicines = GetMedicineUseCase(repository),
            deleteMedicine = DeleteMedicineUseCase(repository)
        )
    }
}