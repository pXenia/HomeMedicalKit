package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.Kit
import com.example.homemedicalkit.dataBase.KitRepository

class DeleteKitUseCase(
    private val repository: KitRepository
    ) {
    suspend operator fun invoke(kit: Kit) {
        repository.delete(kit)
    }
}