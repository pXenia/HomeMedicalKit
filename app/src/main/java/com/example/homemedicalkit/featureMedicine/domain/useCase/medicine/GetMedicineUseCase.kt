package com.example.homemedicalkit.featureMedicine.domain.useCase.medicine

import com.example.homemedicalkit.featureMedicine.domain.model.Medicine
import com.example.homemedicalkit.featureMedicine.domain.repository.MedicineRepository

class GetMedicineUseCase(private val repository: MedicineRepository) {
    suspend operator fun invoke(id: Int?): Medicine? {
        return repository.getMedicine(id)
    }
}