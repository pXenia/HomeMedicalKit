package com.example.homemedicalkit.feature_medicine.domain.useCase.kit

import com.example.homemedicalkit.feature_medicine.domain.model.Kit
import com.example.homemedicalkit.feature_medicine.domain.repository.KitRepository

class GetKitUseCase (private val repository: KitRepository) {
    suspend operator fun invoke(id: Int?): Kit? {
        return repository.getKit(id)
    }
}