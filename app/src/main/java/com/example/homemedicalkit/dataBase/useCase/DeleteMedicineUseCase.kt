package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.MedicineRepository

class DeleteMedicineUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(medicine: Medicine){
        repository.delete(medicine)
    }
}