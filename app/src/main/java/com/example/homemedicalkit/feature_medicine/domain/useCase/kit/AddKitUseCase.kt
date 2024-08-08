package com.example.homemedicalkit.feature_medicine.domain.useCase.kit

import com.example.homemedicalkit.feature_medicine.domain.model.InvalidMedicineException
import com.example.homemedicalkit.feature_medicine.domain.model.Kit
import com.example.homemedicalkit.feature_medicine.domain.repository.KitRepository

class AddKitUseCase(
    private val repository: KitRepository
) {
    @Throws(InvalidMedicineException:: class)
    suspend operator fun invoke(kit : Kit){
        repository.insert(kit)
    }
}