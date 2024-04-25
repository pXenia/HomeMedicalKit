package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.Kit
import com.example.homemedicalkit.dataBase.KitRepository
import kotlinx.coroutines.flow.Flow

class GetKitsUseCase(
    private val repository: KitRepository
) {
    operator fun invoke(
    ): Flow<List<Kit>> {
        return repository.getAll()
    }
}