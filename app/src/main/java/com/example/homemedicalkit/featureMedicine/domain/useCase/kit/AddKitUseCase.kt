package com.example.homemedicalkit.featureMedicine.domain.useCase.kit

import com.example.homemedicalkit.featureMedicine.domain.model.InvalidMedicineException
import com.example.homemedicalkit.featureMedicine.domain.model.Kit
import com.example.homemedicalkit.featureMedicine.domain.repository.KitRepository

class AddKitUseCase(
    private val repository: KitRepository
) {
    @Throws(InvalidMedicineException:: class)
    suspend operator fun invoke(kit : Kit){
        repository.insert(kit)
    }
}