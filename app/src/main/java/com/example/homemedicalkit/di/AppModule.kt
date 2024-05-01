package com.example.homemedicalkit.di

import android.app.Application
import androidx.room.Room
import com.example.homemedicalkit.dataBase.KitRepository
import com.example.homemedicalkit.dataBase.KitRepositoryImpl
import com.example.homemedicalkit.dataBase.MedicalKitDatabase
import com.example.homemedicalkit.dataBase.MedicineRepository
import com.example.homemedicalkit.dataBase.MedicineRepositoryImpl
import com.example.homemedicalkit.dataBase.useCase.AddKitUseCase
import com.example.homemedicalkit.dataBase.useCase.AddMedicineUseCase
import com.example.homemedicalkit.dataBase.useCase.DeleteKitUseCase
import com.example.homemedicalkit.dataBase.useCase.DeleteMedicineUseCase
import com.example.homemedicalkit.dataBase.useCase.GetKitUseCase
import com.example.homemedicalkit.dataBase.useCase.GetKitsUseCase
import com.example.homemedicalkit.dataBase.useCase.GetMedicineUseCase
import com.example.homemedicalkit.dataBase.useCase.GetMedicinesUseCase
import com.example.homemedicalkit.dataBase.useCase.KitUseCases
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
    @Singleton
    fun provideKitRepository(db:MedicalKitDatabase): KitRepository{
        return KitRepositoryImpl(db.kitDao)
    }
    @Provides
    fun provideMedicineUseCases(repository: MedicineRepository):MedicineUseCases{
        return MedicineUseCases(
            getMedicines = GetMedicinesUseCase(repository),
            deleteMedicine = DeleteMedicineUseCase(repository),
            addMedicine = AddMedicineUseCase(repository),
            getMedicine = GetMedicineUseCase(repository)
        )
    }
    @Provides
    fun provideKitUseCases(repository: KitRepository): KitUseCases{
        return KitUseCases(
            getKits = GetKitsUseCase(repository),
            deleteKit = DeleteKitUseCase(repository),
            addKit = AddKitUseCase(repository),
            getKit = GetKitUseCase(repository)
        )
    }
}