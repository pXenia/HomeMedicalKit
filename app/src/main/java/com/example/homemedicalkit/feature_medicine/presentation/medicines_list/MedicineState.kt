package com.example.homemedicalkit.presentation.medicinesList

import com.example.homemedicalkit.feature_medicine.domain.model.Medicine
import com.example.homemedicalkit.feature_medicine.domain.util.MedicineOrder
import com.example.homemedicalkit.feature_medicine.domain.util.OrderType

data class MedicineState(
    val medicines: List<Medicine> = emptyList(),
    val medicineOrder: MedicineOrder = MedicineOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false
)