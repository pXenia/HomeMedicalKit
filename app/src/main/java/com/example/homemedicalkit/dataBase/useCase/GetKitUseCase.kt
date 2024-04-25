package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.Kit
import com.example.homemedicalkit.dataBase.KitRepository

class GetKitUseCase (private val repository: KitRepository) {
    suspend operator fun invoke(id: Int?): Kit? {
        return repository.getKit(id)
    }
}