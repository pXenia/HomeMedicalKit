package com.example.homemedicalkit.feature_medicine.domain.useCase.medicine

import com.example.homemedicalkit.feature_medicine.domain.repository.MedicineRepository

class DeleteMedicineUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(medicineId: Int?){
        repository.delete(medicineId)
    }
}