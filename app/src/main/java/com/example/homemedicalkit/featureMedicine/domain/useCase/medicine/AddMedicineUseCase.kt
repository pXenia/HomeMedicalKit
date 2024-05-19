package com.example.homemedicalkit.featureMedicine.domain.useCase.medicine

import com.example.homemedicalkit.featureMedicine.domain.model.InvalidMedicineException
import com.example.homemedicalkit.featureMedicine.domain.model.Medicine
import com.example.homemedicalkit.featureMedicine.domain.repository.MedicineRepository

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