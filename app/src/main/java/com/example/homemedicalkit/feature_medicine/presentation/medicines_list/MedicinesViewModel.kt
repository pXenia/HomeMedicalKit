package com.example.homemedicalkit.presentation.medicinesList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.feature_medicine.domain.model.Medicine
import com.example.homemedicalkit.feature_medicine.domain.useCase.kit.KitUseCases
import com.example.homemedicalkit.feature_medicine.domain.useCase.medicine.MedicineUseCases
import com.example.homemedicalkit.feature_medicine.domain.util.MedicineOrder
import com.example.homemedicalkit.feature_medicine.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MedicinesViewModel @Inject constructor(
    private val medicineUseCases: MedicineUseCases,
    private val kitUseCases: KitUseCases,
    savedStateHandler: SavedStateHandle
): ViewModel(){
    private val _state = mutableStateOf(MedicineState())
    val state: State<MedicineState> = _state
    private var recentlyDeletedMedicine: Medicine? = null
    private var getMedicineJob: Job? = null
    val kitId =  savedStateHandler.get<Int>("kitId")
    private val _kitName = mutableStateOf("")
    val kitName: State<String> = _kitName

    init{
        if (kitId != -1)
            kitId?.let {
                getMedicines(MedicineOrder.Date(OrderType.Descending), it)
                viewModelScope.launch {
                    kitUseCases.getKit(kitId)?.also { kit ->
                        _kitName.value = kit.kitName
                    }}
            }
        else {
            getAllMedicines(MedicineOrder.Date(OrderType.Descending))
            _kitName.value = "Все\nлекарства"
        }
    }
    fun onEvent(event: MedicineEvent){
        when(event){
            is MedicineEvent.Order -> {
                if (state.value.medicineOrder::class == event.medicineOrder::class &&
                    state.value.medicineOrder.orderType == event.medicineOrder.orderType) {
                    return
                }
                if (kitId != -1){
                    kitId?.let { getMedicines(event.medicineOrder, it) }
                }else{
                    getAllMedicines(event.medicineOrder)
                }

            }
            is MedicineEvent.DeleteMedicine -> {
                viewModelScope.launch {
                    medicineUseCases.deleteMedicine(event.value)
                }

            }
            else -> {}
        }
    }

    private fun getMedicines(medicineOrder: MedicineOrder, kit: Int) {
        getMedicineJob?.cancel()
        getMedicineJob = medicineUseCases.getMedicines(kit, medicineOrder)
            .onEach { medicines ->
                _state.value = state.value.copy(
                    medicines = medicines,
                    medicineOrder = medicineOrder
                    )
            }
            .launchIn(viewModelScope)
    }
    private fun getAllMedicines(medicineOrder: MedicineOrder) {
        getMedicineJob?.cancel()
        getMedicineJob = medicineUseCases.getAllMedicines(medicineOrder)
            .onEach { medicines ->
                _state.value = state.value.copy(
                    medicines = medicines,
                    medicineOrder = medicineOrder
                )
            }
            .launchIn(viewModelScope)
    }
}