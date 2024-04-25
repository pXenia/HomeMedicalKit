package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.InvalidMedicineException
import com.example.homemedicalkit.dataBase.Kit
import com.example.homemedicalkit.dataBase.KitRepository

class AddKitUseCase(
    private val repository: KitRepository
) {
    @Throws(InvalidMedicineException:: class)
    suspend operator fun invoke(kit : Kit){
        repository.insert(kit)
    }
}