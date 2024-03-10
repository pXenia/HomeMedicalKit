package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.InvalidMedicineException
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.MedicineRepository
import kotlin.jvm.Throws

class AddMedicineUseCase(
    private val repository: MedicineRepository
) {
    @Throws(InvalidMedicineException:: class)
    suspend operator fun invoke(medicine : Medicine){
        if (medicine.medicineName.isBlank()){
            throw InvalidMedicineException("Имя не указано")

        }
        repository.insert(medicine)
    }
}