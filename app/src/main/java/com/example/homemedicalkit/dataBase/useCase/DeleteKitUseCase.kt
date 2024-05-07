package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.KitRepository

class DeleteKitUseCase(
    private val repository: KitRepository
    ) {
    suspend operator fun invoke(kitId: Int?) {
        repository.delete(kitId)
    }
}