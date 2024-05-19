package com.example.homemedicalkit.featureMedicine.domain.useCase.kit

import com.example.homemedicalkit.featureMedicine.domain.model.Kit
import com.example.homemedicalkit.featureMedicine.domain.repository.KitRepository
import kotlinx.coroutines.flow.Flow

class GetKitsUseCase(
    private val repository: KitRepository
) {
    operator fun invoke(
    ): Flow<List<Kit>> {
        return repository.getAll()
    }
}