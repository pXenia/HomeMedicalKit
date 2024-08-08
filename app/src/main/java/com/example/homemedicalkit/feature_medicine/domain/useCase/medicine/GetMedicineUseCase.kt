package com.example.homemedicalkit.feature_medicine.domain.useCase.medicine

import com.example.homemedicalkit.feature_medicine.domain.model.Medicine
import com.example.homemedicalkit.feature_medicine.domain.repository.MedicineRepository

class GetMedicineUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(id: Int?): Medicine? {
        return repository.getMedicine(id)
    }
}