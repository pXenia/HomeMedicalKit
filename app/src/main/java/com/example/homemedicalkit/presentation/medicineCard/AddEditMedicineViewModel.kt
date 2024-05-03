package com.example.homemedicalkit.presentation.medicineCard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.dataBase.InvalidMedicineException
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.useCase.KitUseCases
import com.example.homemedicalkit.dataBase.useCase.MedicineUseCases
import com.example.homemedicalkit.presentation.medicinesList.MedicineImageState
import com.example.homemedicalkit.presentation.medicinesList.MedicineTextFieldStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditMedicineViewModel @Inject constructor(
    private val medicineUseCases: MedicineUseCases,
    private val kitUseCases: KitUseCases,
    savedStateHandler: SavedStateHandle
): ViewModel() {
    private var getKitsJob: Job? = null

    private val _medicineName = mutableStateOf(MedicineTextFieldStates())
    val medicineName: State<MedicineTextFieldStates> = _medicineName

    private val _medicineDate = mutableStateOf(0L)
    val medicineDate: State<Long> = _medicineDate

    private val _medicineFew = mutableStateOf(false)
    val medicineFew: State<Boolean> = _medicineFew

    private val _medicineTags = mutableStateOf(MedicineTextFieldStates())
    val medicineTags: State<MedicineTextFieldStates> = _medicineTags

    private val _medicineKit = mutableStateOf( savedStateHandler.get<Int>("kitId") ?: -1)
    val medicineKit: State<Int> = _medicineKit

    private var _kitsNames: MutableMap<Int,String> = mutableMapOf()
    val stateKits: MutableMap<Int,String> = _kitsNames

    private val _medicineImage = mutableStateOf(MedicineImageState(imageUri = ""))
    val medicineImage: State<MedicineImageState> = _medicineImage

    private val _medicineDescription = mutableStateOf(MedicineTextFieldStates())
    val medicineDescription: State<MedicineTextFieldStates> = _medicineDescription


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentMedicineId: Int? = null
    init {
        Log.d("VMCard", savedStateHandler.toString())
        getKits()
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
                        _medicineKit.value = medicine.medicineKit
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
            is AddEditMedicineEvent.EnteredKit -> {
                _medicineKit.value = event.value }
            is AddEditMedicineEvent.SaveMedicine ->{
                viewModelScope.launch {
                    try{
                        medicineUseCases.addMedicine(
                            Medicine(
                                medicineName = medicineName.value.text,
                                medicineDate = medicineDate.value,
                                medicineDescription = medicineDescription.value.text,
                                medicineKit = medicineKit.value,
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

    private fun getKits() {
        getKitsJob?.cancel()
        getKitsJob = kitUseCases.getKits()
            .onEach { kits ->
                kits.onEach {
                    _kitsNames[it.kitId!!] = it.kitName
                }
            }.launchIn(viewModelScope)
    }
    sealed class UiEvent{
        data class ShowSnackBar (val message: String): UiEvent()
        object SaveMedicine: UiEvent()
    }

}