package com.example.homemedicalkit.presentation.medicinesList

import com.example.homemedicalkit.feature_medicine.domain.util.MedicineOrder

sealed class MedicineEvent {
    data class Order (val medicineOrder: MedicineOrder): MedicineEvent()
    data class DeleteMedicine(val value: Int): MedicineEvent()
}