package com.example.homemedicalkit.feature_medicine.domain.useCase.kit

import com.example.homemedicalkit.feature_medicine.domain.model.Kit
import com.example.homemedicalkit.feature_medicine.domain.repository.KitRepository
import kotlinx.coroutines.flow.Flow

class GetKitsUseCase(
    private val repository: KitRepository
) {
    operator fun invoke(
    ): Flow<List<Kit>> {
        return repository.getAll()
    }
}