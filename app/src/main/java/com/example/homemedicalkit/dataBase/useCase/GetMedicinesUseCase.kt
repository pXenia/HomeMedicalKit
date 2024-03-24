package com.example.homemedicalkit.dataBase.useCase

import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.MedicineOrder
import com.example.homemedicalkit.dataBase.MedicineRepository
import com.example.homemedicalkit.dataBase.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetMedicinesUseCase(
    private val repository: MedicineRepository
) {
    operator fun invoke(
        medicineOrder: MedicineOrder = MedicineOrder.Date(OrderType.Descending)
    ): Flow<List<Medicine>> {
        return repository.getAll().map { medicines ->
            when (medicineOrder.orderType){
                is OrderType.Ascending ->{
                    when(medicineOrder) {
                        is MedicineOrder.Name -> medicines.sortedBy { it.medicineName.lowercase() }
                        is MedicineOrder.Date -> medicines.sortedBy { it.medicineDate}
                        is MedicineOrder.Color -> medicines.sortedBy { it.medicineNumberFew}
                    }
                }
                is OrderType.Descending ->{
                    when(medicineOrder) {
                        is MedicineOrder.Name -> medicines.sortedByDescending { it.medicineName.lowercase() }
                        is MedicineOrder.Date -> medicines.sortedByDescending { it.medicineDate}
                        is MedicineOrder.Color -> medicines.sortedByDescending { it.medicineNumberFew}
                    }
                }
            }

        }
    }
}