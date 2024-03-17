package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.MedicineRepository

class GetMedicineUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(id: Long): Medicine?{
        return repository.getMedicine(id)
    }
}