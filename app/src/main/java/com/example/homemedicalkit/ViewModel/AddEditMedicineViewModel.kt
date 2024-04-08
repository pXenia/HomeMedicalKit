package com.example.homemedicalkit.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.dataBase.InvalidMedicineException
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.useCase.MedicineUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddEditMedicineViewModel @Inject constructor(
    private val medicineUseCases: MedicineUseCases,
    savedStateHandler: SavedStateHandle
): ViewModel() {

    private val _medicineName = mutableStateOf(MedicineTextFieldStates(
        hint = "Название"
    ))
    val medicineName: State<MedicineTextFieldStates> = _medicineName

    private val _medicineDate = mutableStateOf(MedicineTextFieldStates(
        hint = "01.01.2070"
    ))
    val medicineDate: State<MedicineTextFieldStates> = _medicineDate

    private val _medicineFew = mutableStateOf(false)
    val medicineFew: State<Boolean> = _medicineFew

    private val _medicineTags = mutableStateOf(MedicineTextFieldStates())
    val medicineTags: State<MedicineTextFieldStates> = _medicineTags

    private val _medicineImage = mutableStateOf(MedicineImageState(imageUri = ""))
    val medicineImage: State<MedicineImageState> = _medicineImage

    private val _medicineDescription = mutableStateOf(MedicineTextFieldStates(
        hint = "Описание "
    ))
    val medicineDescription: State<MedicineTextFieldStates> = _medicineDescription


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentMedicineId: Int? = null
    init {
        savedStateHandler.get<Int?>("medicineId")?.let{ medicineId ->
            if (medicineId != -1){
                viewModelScope.launch {
                    medicineUseCases.getMedicine(medicineId)?.also {  medicine ->
                        currentMedicineId = medicine.medicineId
                        _medicineName.value = medicineName.value.copy(
                            text = medicine.medicineName,
                            isHintVisible = false
                        )
                        _medicineDate.value = medicineDate.value.copy(
                            text = medicine.medicineDate,
                            isHintVisible = false
                        )
                        _medicineDescription.value = medicineDescription.value.copy(
                            text = medicine.medicineDescription,
                            isHintVisible = false
                        )
                        _medicineFew.value = medicine.medicineNumberFew
                        _medicineImage.value = medicineImage.value.copy(
                            imageUri = medicine.medicineImage
                        )

                    }
                }
            }
        }
    }
    fun onEvent(event: AddEditMedicineEvent){
        when(event){
            is AddEditMedicineEvent.EnteredName ->{
                _medicineName.value = medicineName.value.copy(
                    text = event.value
                )
            }
            is AddEditMedicineEvent.ChangeFocusName ->{
                _medicineName.value = medicineName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            medicineName.value.text.isBlank()
                )
            }
            is AddEditMedicineEvent.EnteredDate ->{
                _medicineDate.value = medicineDate.value.copy(
                    text = event.value
                )
            }
            is AddEditMedicineEvent.ChangeFocusDate ->{
                _medicineDate.value = medicineDate.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            medicineDate.value.text.isBlank()
                )
            }
            is AddEditMedicineEvent.EnteredDescription ->{
                _medicineDescription.value = medicineDescription.value.copy(
                    text = event.value
                )
            }
            is AddEditMedicineEvent.ChangeFocusDescription ->{
                _medicineDescription.value = medicineDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            medicineDate.value.text.isBlank()
                )
            }
            is AddEditMedicineEvent.EnteredMedicineFew -> {
                _medicineFew.value = !medicineFew.value
            }
            is AddEditMedicineEvent.EnteredImage -> {
                File(_medicineImage.value.imageUri).delete()
                _medicineImage.value = medicineImage.value.copy(
                    imageUri = event.value
                )
            }
            is AddEditMedicineEvent.SaveMedicine ->{
                viewModelScope.launch {
                    try{
                        medicineUseCases.addMedicine(
                            Medicine(
                                medicineName = medicineName.value.text,
                                medicineDate = medicineDate.value.text,
                                medicineDescription = medicineDescription.value.text,
                                medicineKit = 1,
                                medicineNumberFew = medicineFew.value,
                                medicineImage = medicineImage.value.imageUri,
                                medicineId = currentMedicineId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveMedicine)

                    } catch(e: InvalidMedicineException){
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Лекарство не сохранено"
                            )
                        )

                    }
                }
            }

        }
    }

    sealed class UiEvent{
        data class ShowSnackBar (val message: String): UiEvent()
        object SaveMedicine: UiEvent()
    }

}