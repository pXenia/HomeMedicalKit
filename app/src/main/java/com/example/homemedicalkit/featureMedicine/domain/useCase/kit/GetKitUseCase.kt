package com.example.homemedicalkit.featureMedicine.domain.useCase.kit

import com.example.homemedicalkit.featureMedicine.domain.model.Kit
import com.example.homemedicalkit.featureMedicine.domain.repository.KitRepository

class GetKitUseCase (private val repository: KitRepository) {
    suspend operator fun invoke(id: Int?): Kit? {
        return repository.getKit(id)
    }
}