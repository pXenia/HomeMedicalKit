package com.example.homemedicalkit.di

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.room.Room
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.homemedicalkit.featureMedicine.data.dataSourse.MedicalKitDatabase
import com.example.homemedicalkit.featureMedicine.data.repository.KitRepositoryImpl
import com.example.homemedicalkit.featureMedicine.data.repository.MedicineRepositoryImpl
import com.example.homemedicalkit.featureMedicine.domain.repository.KitRepository
import com.example.homemedicalkit.featureMedicine.domain.repository.MedicineRepository
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.AddKitUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.DeleteKitUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.GetKitUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.GetKitsUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.kit.KitUseCases
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.AddMedicineUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.DeleteMedicineUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.GetAllMedicineUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.GetMedicineUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.GetMedicinesUseCase
import com.example.homemedicalkit.featureMedicine.domain.useCase.medicine.MedicineUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMedicalKitDatabase(app:Application): MedicalKitDatabase {
       return  Room.databaseBuilder(
           app,
           MedicalKitDatabase::class.java,
           MedicalKitDatabase.DATABASE_NAME
           ).build()
    }
    @Provides
    @Singleton
    fun provideMedicineRepository(db: MedicalKitDatabase): MedicineRepository {
        return MedicineRepositoryImpl(db.medicineDao)
    }
    @Provides
    @Singleton
    fun provideKitRepository(db: MedicalKitDatabase): KitRepository {
        return KitRepositoryImpl(db.kitDao)
    }
    @Provides
    fun provideMedicineUseCases(repository: MedicineRepository): MedicineUseCases {
        return MedicineUseCases(
            getMedicines = GetMedicinesUseCase(repository),
            deleteMedicine = DeleteMedicineUseCase(repository),
            addMedicine = AddMedicineUseCase(repository),
            getMedicine = GetMedicineUseCase(repository),
            getAllMedicines = GetAllMedicineUseCase(repository)

        )
    }
    @Provides
    fun provideKitUseCases(repository: KitRepository): KitUseCases {
        return KitUseCases(
            getKits = GetKitsUseCase(repository),
            deleteKit = DeleteKitUseCase(repository),
            addKit = AddKitUseCase(repository),
            getKit = GetKitUseCase(repository),
        )
    }
}
@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {
    @Provides
    @Singleton
    fun provideConfiguration(workerFactory: HiltWorkerFactory): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext appContext: Context,
        configuration: Configuration
    ): WorkManager {
        WorkManager.initialize(appContext, configuration)
        return WorkManager.getInstance(appContext)
    }
}