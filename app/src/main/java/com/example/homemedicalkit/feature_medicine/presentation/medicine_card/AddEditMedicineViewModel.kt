package com.example.homemedicalkit.presentation.medicineCard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.feature_medicine.domain.model.InvalidMedicineException
import com.example.homemedicalkit.feature_medicine.domain.model.Medicine
import com.example.homemedicalkit.feature_medicine.domain.useCase.kit.KitUseCases
import com.example.homemedicalkit.feature_medicine.domain.useCase.medicine.MedicineUseCases
import com.example.homemedicalkit.presentation.medicinesList.MedicineImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private val _medicineName = mutableStateOf("")
    val medicineName: State<String> = _medicineName

    private val _medicineDate = mutableStateOf(0L)
    val medicineDate: State<Long> = _medicineDate

    private val _medicineFew = mutableStateOf(false)
    val medicineFew: State<Boolean> = _medicineFew

    private val _medicineKit = mutableStateOf( savedStateHandler.get<Int>("kitId") ?:  -1)
    val medicineKit: State<Int> = _medicineKit

    private var _kitsNames: MutableMap<Int,String> = mutableMapOf()
    val stateKits: MutableMap<Int,String> = _kitsNames

    private val _medicineImage = mutableStateOf(MedicineImageState(imageUri = ""))
    val medicineImage: State<MedicineImageState> = _medicineImage

    private val _medicineDescription = mutableStateOf("")
    val medicineDescription: State<String> = _medicineDescription

    var currentMedicineId: Int? = null
    init {
        getKits()
        savedStateHandler.get<Int?>("medicineId")?.let{ medicineId ->
            if (medicineId != -1){
                viewModelScope.launch {
                    medicineUseCases.getMedicine(medicineId)?.also {  medicine ->
                        currentMedicineId = medicine.medicineId
                        _medicineName.value =  medicine.medicineName
                        _medicineDate.value = medicine.medicineDate
                        _medicineDescription.value = medicine.medicineDescription
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
                _medicineName.value = event.value
            }
            is AddEditMedicineEvent.EnteredDate ->{
                _medicineDate.value = event.value
            }
            is AddEditMedicineEvent.EnteredDescription ->{
                _medicineDescription.value = event.value
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
                                medicineName = medicineName.value,
                                medicineDate = medicineDate.value,
                                medicineDescription = medicineDescription.value,
                                medicineKit = medicineKit.value,
                                medicineNumberFew = medicineFew.value,
                                medicineImage = medicineImage.value.imageUri,
                                medicineId = currentMedicineId
                            )
                        )
                    } catch(e: InvalidMedicineException){

                    }
                }
            }
            else -> {}
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

}