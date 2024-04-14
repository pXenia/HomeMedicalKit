package com.example.homemedicalkit.ViewModel

import androidx.compose.ui.focus.FocusState

sealed class AddEditMedicineEvent {
    data class EnteredName(val value: String): AddEditMedicineEvent()
    data class ChangeFocusName(val focusState: FocusState): AddEditMedicineEvent()
    data class EnteredDate(val value: Long): AddEditMedicineEvent()
    data class EnteredMedicineFew(val value: Boolean): AddEditMedicineEvent()
    data class EnteredDescription(val value: String): AddEditMedicineEvent()
    data class EnteredImage(val value: String): AddEditMedicineEvent()
    object SaveMedicine: AddEditMedicineEvent()
}
