package com.example.homemedicalkit.presentation.medicineCard

sealed class AddEditMedicineEvent {
    data class EnteredName(val value: String): AddEditMedicineEvent()
    data class EnteredDate(val value: Long): AddEditMedicineEvent()
    data class EnteredMedicineFew(val value: Boolean): AddEditMedicineEvent()
    data class EnteredDescription(val value: String): AddEditMedicineEvent()
    data class EnteredImage(val value: String): AddEditMedicineEvent()
    data class EnteredKit(val value: Int): AddEditMedicineEvent()
    object SaveMedicine: AddEditMedicineEvent()
}
