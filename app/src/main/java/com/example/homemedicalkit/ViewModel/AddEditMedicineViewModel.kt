package com.example.homemedicalkit.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.homemedicalkit.dataBase.useCase.MedicineUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.Date
import javax.inject.Inject

class AddEditMedicineViewModel @Inject constructor(
    private val medicineUseCases: MedicineUseCases
): ViewModel() {
    private val _medicineName = mutableStateOf(MedicineTextFieldStates(
        hint = "Название ..."
    ))
    val medicineName: State<MedicineTextFieldStates> = _medicineName

    private val _medicineDate = mutableStateOf(MedicineTextFieldStates(
        hint = "01.01.2010"
    ))
    val medicineDate: State<MedicineTextFieldStates> = _medicineDate

    private val _medicineFew = mutableStateOf(MedicineTextFieldStates())
    val medicineFew: State<MedicineTextFieldStates> = _medicineFew

    private val _medicineTags = mutableStateOf(MedicineTextFieldStates())
    val medicineTags: State<MedicineTextFieldStates> = _medicineTags

    private val _medicineImage = mutableStateOf(MedicineTextFieldStates())
    val medicineImage: State<MedicineTextFieldStates> = _medicineImage

    private val _medicineDescription = mutableStateOf(MedicineTextFieldStates(
        hint = "Описание ..."
    ))
    val medicineDescription: State<MedicineTextFieldStates> = _medicineDescription

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent{
        data class ShowSnackBar (val message: String): UiEvent()
        object SaveMedicine: UiEvent()
    }

}