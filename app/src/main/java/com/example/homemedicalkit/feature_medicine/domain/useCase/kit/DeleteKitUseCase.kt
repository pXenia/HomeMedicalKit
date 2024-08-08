package com.example.homemedicalkit.feature_medicine.domain.useCase.kit

import com.example.homemedicalkit.feature_medicine.domain.repository.KitRepository

class DeleteKitUseCase(
    private val repository: KitRepository
    ) {
    suspend operator fun invoke(kitId: Int?) {
        repository.delete(kitId)
    }
}