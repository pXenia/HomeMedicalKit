package com.example.homemedicalkit.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homemedicalkit.dataBase.useCase.KitUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class KitsViewModel@Inject constructor(
    private val kitUseCases: KitUseCases
): ViewModel() {
    private val _state = mutableStateOf(KitState())
    val state: State<KitState> = _state
    private var getKitsJob: Job? = null

    init {
        getKits()
    }

    fun onEvent(event: KitsEvent) {
        when (event) {
            is KitsEvent.DeleteKit -> {
                viewModelScope.launch {
                    kitUseCases.deleteKit(event.kit)
                }
            }
        }
    }

    private fun getKits() {
        getKitsJob?.cancel()
        getKitsJob = kitUseCases.getKits()
            .onEach { kit ->
                _state.value = state.value.copy(
                    kits = kit
                )
            }.launchIn(viewModelScope)
    }

}