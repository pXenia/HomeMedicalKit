package com.example.homemedicalkit.featureMedicine.domain.useCase.kit

import com.example.homemedicalkit.featureMedicine.domain.repository.KitRepository

class DeleteKitUseCase(
    private val repository: KitRepository
    ) {
    suspend operator fun invoke(kitId: Int?) {
        repository.delete(kitId)
    }
}