package com.example.homemedicalkit.presentation.medicinesList

import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.MedicineOrder

sealed class MedicineEvent {
    data class Order (val medicineOrder: MedicineOrder): MedicineEvent()
    data class DeleteMedicine(val medicine: Medicine): MedicineEvent()
    object  RestoreMedicine: MedicineEvent()
    object  ToggleOrderSection: MedicineEvent()
}