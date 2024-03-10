package com.example.homemedicalkit.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.dataBase.MedicineDao
import com.example.homemedicalkit.dataBase.Medicine
import com.example.homemedicalkit.dataBase.MedicineOrder
import com.example.homemedicalkit.dataBase.OrderType
import com.example.homemedicalkit.dataBase.useCase.MedicineUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MedicalKitViewModel @Inject constructor(
    private val medicineUseCases: MedicineUseCases
): ViewModel(){
    private val _state = mutableStateOf(MedicineState())
    val state: State<MedicineState> = _state
    private var recentlyDeletedMedicine: Medicine? = null
    private var getMedicineJob: Job? = null
    init{
        getMedicines(MedicineOrder.Date(OrderType.Descending))
    }
    fun onEvent(event: MedicineEvent){
        when(event){
            is MedicineEvent.Order -> {
                if (state.value.medicineOrder::class == event.medicineOrder::class &&
                    state.value.medicineOrder.orderType == event.medicineOrder.orderType) {
                    return
                }
                getMedicines(event.medicineOrder)

            }
            is MedicineEvent.DeleteMedicine -> {
                viewModelScope.launch {
                    medicineUseCases.deleteMedicine(event.medicine)
                    recentlyDeletedMedicine = event.medicine
                }

            }
            is MedicineEvent.RestoreMedicine -> {
                viewModelScope.launch {
                    medicineUseCases.addMedicine(recentlyDeletedMedicine?: return@launch)
                    recentlyDeletedMedicine = null
                }


            }
            is MedicineEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }
    }

    private fun getMedicines(medicineOrder: MedicineOrder) {
        getMedicineJob?.cancel()
        getMedicineJob = medicineUseCases.getMedicines(medicineOrder)
            .onEach { medicines ->
                _state.value = state.value.copy(
                    medicines = medicines,
                    medicineOrder = medicineOrder
                    )

            }
            .launchIn(viewModelScope)
    }
}