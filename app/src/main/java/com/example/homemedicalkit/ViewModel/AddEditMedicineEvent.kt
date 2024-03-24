package com.example.homemedicalkit.ViewModel

import androidx.compose.ui.focus.FocusState

sealed class AddEditMedicineEvent {
    data class EnteredName(val value: String): AddEditMedicineEvent()
    data class ChangeFocusName(val focusState: FocusState): AddEditMedicineEvent()
    data class EnteredDate(val value: String): AddEditMedicineEvent()
    data class EnteredMedicineFew(val value: Boolean): AddEditMedicineEvent()
    data class ChangeFocusDate(val focusState: FocusState): AddEditMedicineEvent()
    data class EnteredDescription(val value: String): AddEditMedicineEvent()
    data class ChangeFocusDescription(val focusState: FocusState): AddEditMedicineEvent()
    object SaveMedicine: AddEditMedicineEvent()
}
