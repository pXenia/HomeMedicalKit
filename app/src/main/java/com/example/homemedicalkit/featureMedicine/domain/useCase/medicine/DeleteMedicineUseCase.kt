package com.example.homemedicalkit.featureMedicine.domain.useCase.medicine

import com.example.homemedicalkit.featureMedicine.domain.repository.MedicineRepository

class DeleteMedicineUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(medicineId: Int?){
        repository.delete(medicineId)
    }
}