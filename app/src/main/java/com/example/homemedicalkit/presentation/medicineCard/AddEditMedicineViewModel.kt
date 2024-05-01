package com.example.homemedicalkit.presentation.medicineCard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.presentation.medicinesList.MedicineImageState
import com.example.homemedicalkit.presentation.medicinesList.MedicineTextFieldStates
import com.example.homemedicalkit.dataBase.InvalidMedicineException
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.useCase.MedicineUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditMedicineViewModel @Inject constructor(
    private val medicineUseCases: MedicineUseCases,
    savedStateHandler: SavedStateHandle
): ViewModel() {

    private val _medicineName = mutableStateOf(MedicineTextFieldStates())
    val medicineName: State<MedicineTextFieldStates> = _medicineName

    private val _medicineDate = mutableStateOf(0L)
    val medicineDate: State<Long> = _medicineDate

    private val _medicineFew = mutableStateOf(false)
    val medicineFew: State<Boolean> = _medicineFew

    private val _medicineTags = mutableStateOf(MedicineTextFieldStates())
    val medicineTags: State<MedicineTextFieldStates> = _medicineTags

    private val _medicineImage = mutableStateOf(MedicineImageState(imageUri = ""))
    val medicineImage: State<MedicineImageState> = _medicineImage

    private val _medicineDescription = mutableStateOf(MedicineTextFieldStates())
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
                        _medicineDate.value = medicine.medicineDate

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
                _medicineDate.value = event.value
            }
            is AddEditMedicineEvent.EnteredDescription ->{
                _medicineDescription.value = medicineDescription.value.copy(
                    text = event.value
                )
            }
            is AddEditMedicineEvent.EnteredMedicineFew -> {
                _medicineFew.value = !medicineFew.value
            }
            is AddEditMedicineEvent.EnteredImage -> {
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
                                medicineDate = medicineDate.value,
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