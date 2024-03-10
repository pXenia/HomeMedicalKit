package com.example.homemedicalkit.ViewModel

import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.MedicineOrder
import com.example.homemedicalkit.dataBase.OrderType

data class MedicineState(
    val medicines: List<Medicine> = emptyList(),
    val medicineOrder: MedicineOrder = MedicineOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false
)