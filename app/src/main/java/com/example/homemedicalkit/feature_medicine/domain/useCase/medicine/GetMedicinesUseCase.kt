package com.example.homemedicalkit.feature_medicine.domain.useCase.medicine

import com.example.homemedicalkit.feature_medicine.domain.model.Medicine
import com.example.homemedicalkit.feature_medicine.domain.util.MedicineOrder
import com.example.homemedicalkit.feature_medicine.domain.repository.MedicineRepository
import com.example.homemedicalkit.feature_medicine.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetMedicinesUseCase(
    private val repository: MedicineRepository
) {
    operator fun invoke(
        kit: Int,
        medicineOrder: MedicineOrder = MedicineOrder.Name(OrderType.Descending)
    ): Flow<List<Medicine>> {
        return repository.getAllFromKit(kit).map { medicines ->
            when (medicineOrder.orderType){
                is OrderType.Ascending ->{
                    when(medicineOrder) {
                        is MedicineOrder.Name -> medicines.sortedBy { it.medicineName.lowercase() }
                        is MedicineOrder.Date -> medicines.sortedBy { it.medicineDate}
                        is MedicineOrder.Few -> medicines.sortedBy { it.medicineNumberFew}
                    }
                }
                is OrderType.Descending ->{
                    when(medicineOrder) {
                        is MedicineOrder.Name -> medicines.sortedByDescending { it.medicineName.lowercase() }
                        is MedicineOrder.Date -> medicines.sortedByDescending { it.medicineDate}
                        is MedicineOrder.Few -> medicines.sortedByDescending { it.medicineNumberFew}
                    }
                }
            }

        }
    }
}